package it.paperdragon.scala.cake


/**
 * Service component
 */
trait ServiceComponent {
  self: RepositoryComponent =>

  def service: Service

  trait Service {
    def createStuff(in: String): String
  }

  class ProdService extends Service {
    def createStuff(in: String) = repository.save(in)
  }

}
