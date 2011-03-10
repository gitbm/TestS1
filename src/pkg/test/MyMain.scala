package pkg.test
import java.io.File
import org.eclipse.jgit.lib.RepositoryBuilder
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.transport.RefSpec
import scala.io._
import org.eclipse.jgit.merge.MergeStrategy
import org.eclipse.jgit.transport.CredentialsProvider
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider

object MyMain {

	val wkdir = "gr1"
	val gitCommonBase = "/home/user1/workspace-rap"
	val gitRepoName = "rem"
	//val gitRemote = "git@github.com:gitbm/rem.git"
	val gitRemoteAlias = "origin"
	val gitRemote = gitCommonBase + File.separator + gitRepoName
	val gitDirBase = gitCommonBase + File.separator + wkdir + File.separator + gitRepoName
	val gitDirRepo = gitDirBase + "/.git"
	val fileName = "temp3.txt"
	val fullFileName = gitDirBase + File.separator + fileName
	val gdFile = new File(gitDirRepo)
	val builder = new RepositoryBuilder();
		val repository = builder.setGitDir(gdFile)
		.readEnvironment() // scan environment GIT_* variables
		.findGitDir() // scan up the file system tree
		.build();
	val git = new Git(repository)


  def fetch = {
	git.fetch().setRemote("ghremro").call();
	//git.fetch().setRemote("origin").setRefSpecs(new RefSpec("+master:origin/master")).call();
	
	}
  
  def merge = {
	  val ref = repository.getRef("refs/remotes/origin/master");
	  git.merge().setStrategy(MergeStrategy.RESOLVE).include(ref).call()
  }
  
  def push = {	
	try { 
//		git.add().addFilepattern(fileName).call()
//		println("after add");
//		git.commit().setMessage("Committing stuff").call()
//		println("after commit")
		git.push().setRemote("ghrem").setRefSpecs(new RefSpec("master")).call()
		println("after push")
	}
	catch {
		case e => { println("Git Exception: " + e); e.printStackTrace() }
	}

  }
	
  def main(args: Array[String]): Unit = {  
	  println("Hooray!!")
//	  try {
//		  val s = Source.fromFile("/home/user1/workspace-rap/gr1/rem/text3.txt")
//		  val l = s.getLines
//		  l.foreach(s => println("l = " + s))
//	  }
//	  catch {
//	 	  case e => println("File does not exist");
//	  }
	  
	  push
	  //fetch
	  //merge

  }

}