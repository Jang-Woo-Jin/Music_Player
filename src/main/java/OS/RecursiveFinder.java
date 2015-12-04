package OS;

/**
 * Sample code that finds files that match the specified glob pattern.
 * For more information on what constitutes a glob pattern, see
 * https://docs.oracle.com/javase/tutorial/essential/io/fileOps.html#glob
 * <p>
 * The file or directories that match the pattern are printed to
 * standard out.  The number of matches is also printed.
 * <p>
 * When executing this application, you must put the glob pattern
 * in quotes, so the shell will not expand any wild cards:
 * java Find . -name "*.java"
 * <p>
 * URL: http://docs.oracle.com/javase/tutorial/essential/io/find.html
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import static java.nio.file.FileVisitResult.CONTINUE;


public class RecursiveFinder {
    private final Path startingDir;
    private final String pattern;

    public RecursiveFinder(String startingDir, String pattern) {
        this.startingDir = Paths.get(startingDir);
        this.pattern = pattern;
    }

    public ArrayList<Path> find() throws IOException {
        Finder finder = new Finder(this.pattern);
        Files.walkFileTree(startingDir, finder);
        return finder.done();
    }
//    public static void main(String[] args)
//            throws IOException {
//
//        Finder finder = new Finder(pattern);
//        Files.walkFileTree(startingDir, finder);
//        finder.done();
//    }

    public static class Finder
            extends SimpleFileVisitor<Path> {

        private final PathMatcher matcher;
        private final ArrayList<Path> files = new ArrayList<>();

        Finder(String pattern) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
        }

        // Compares the glob pattern against
        // the file or directory name.
        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                files.add(file);
            }
        }

        // Prints the total number of
        // matches to standard out.
        ArrayList<Path> done() {
            return this.files;
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }

        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) {
            find(dir);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }
}
