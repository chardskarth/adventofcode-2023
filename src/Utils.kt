import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T> T.letWhenTrue(predicate: Boolean, block: () -> T): T {
    return if(predicate) block() else this
}
fun <T> T.letWhenNotTrue(predicate: Boolean, block: () -> T): T {
    return if(!predicate) block() else this
}

fun Boolean.alsoWhenTrue(block: () -> Unit): Boolean {
    if(this) block()
    return this
}

fun <T> List<T>.alsoForEach(block: (T) -> Unit): List<T> {
    this.forEach(block)
    return this
}

infix fun <A,B, C> Pair<A,B>.andTo(other: C) = Triple(this.first, this.second, other)