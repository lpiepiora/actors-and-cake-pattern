package it.paperdragon.scala.cake

/**
 * A repository component, used by [[ServiceComponent]]
 */
trait RepositoryComponent {

  def repository: Repository

  trait Repository {
    def save(in: String): String
  }

  class ProdRepository extends Repository {
    override def save(in: String) = s"Saved by production repository: '$in'"
  }

}
