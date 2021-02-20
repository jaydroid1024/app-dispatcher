package dispatcher.plugin

import com.android.build.api.transform.*
import com.google.common.collect.ImmutableSet
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */
class AppLifecycleTransform extends Transform {

    Project project

    AppLifecycleTransform(Project project) {
        this.project = project
    }

    @Override
    String getName() {
        return "LifeCycleTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return ImmutableSet.of(QualifiedContent.DefaultContentType.CLASSES)

    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return ImmutableSet.of(QualifiedContent.Scope.PROJECT, QualifiedContent.Scope.SUB_PROJECTS,
                QualifiedContent.Scope.EXTERNAL_LIBRARIES)
    }

    @Override
    boolean isIncremental() {
        return true
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)

        println "--------- AppDispatcher,AppLifecycleTransform 开始转换-------------->>>>>>>"

        def appLifecycleCallbackList = []

        transformInvocation.getInputs().each { TransformInput input ->

            input.directoryInputs.each { DirectoryInput directoryInput ->

                if (directoryInput.file.isDirectory()) {
                    directoryInput.file.eachFileRecurse { File file ->
                        //形如 AppLife$$****$$Proxy.class 的类，是我们要找的目标class
                        if (ScanUtil.isTargetProxyClass(file)) {
                            appLifecycleCallbackList.add(file.name)
                        }
                    }
                }

                def dest = transformInvocation.getOutputProvider().getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            input.jarInputs.each { JarInput jarInput ->
                println "\njarInput = ${jarInput}"

                def jarName = jarInput.name
                def md5 = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }

                def dest = transformInvocation.getOutputProvider().getContentLocation(jarName + md5, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                if (jarInput.file.getAbsolutePath().endsWith(".jar")) {
                    //处理jar包里的代码
                    File src = jarInput.file
                    if (ScanUtil.shouldProcessPreDexJar(src.absolutePath)) {
                        List<String> list = ScanUtil.scanJar(src, dest)
                        if (list != null) {
                            appLifecycleCallbackList.addAll(list)
                        }
                    }
                }
                FileUtils.copyFile(jarInput.file, dest)
            }
        }

        if (appLifecycleCallbackList.empty) {
            println " LifeCycleTransform appLifecycleCallbackList empty"
        } else {
            println " LifeCycleTransform appLifecycleCallbackList：  "+appLifecycleCallbackList

            //
            new AppLifecycleCodeInjector(appLifecycleCallbackList).execute()
        }

        println "LifeCycleTransform transform finish----------------<<<<<<<\n"

    }

}
