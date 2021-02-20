package dispatcher.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */
class AppLifecyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "------LifeCycle plugin apply 方法执行了 -------"
        println "AppLifecyclePlugin, apply(), project: " + project
        def android = project.extensions.getByType(AppExtension)
        println "------LifeCycle plugin android：-------" + android
        android.registerTransform(new AppLifecycleTransform(project))
    }

}