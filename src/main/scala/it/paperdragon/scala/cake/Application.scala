package it.paperdragon.scala.cake

/**
 * An abstract application trait. This provides all dependencies to actors
 */
trait Application
  extends ServiceComponent
  with RepositoryComponent

/**
 * This class provides a production ready configuration for the application
 */
class DefaultApplication extends Application {
  // we declare vals as lazy, so they can be overridden by subclasses (for testing)
  override lazy val repository: Repository = new ProdRepository
  override lazy val service: Service = new ProdService
}
