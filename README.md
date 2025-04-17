# PostcodeSearch

📍 How to start the project:

💛 Step 1: Please pull initial structure file (from main)

💙 Step 2: Each team member creates and works on their own branch.
For example:
feature/bst-implementation
feature/avl-implementation
feature/minheap-implementation
feature/benchmarking-and-ui

💜 Step3: Pull Requests & Merging
When we completes a feature, please create a pull request to main.
The rest of the group can review and approve.

💚 Step4: Benchmark
Once all versions (BST, AVL, MinHeap) are working and merged into main, I'll be working on benchmarking tests.

_______________________________________

⚠️ Compilation & Runtime Note
If you encounter an error like this when running the app:

Exception in thread "main" java.lang.Error: Unresolved compilation problems:
    AVL cannot be resolved to a type
    AVLMenu cannot be resolved to a type
    ...

Fix in 3 Steps
1. Delete all old compiled files: 
    rm -rf bin/*

2. Recompile all source files Run this from the project root:
    javac -d bin src/**/*.java    OR    javac -d bin $(find src -name "*.java")

3. Run the application
    java -cp bin App

💡 Why This Happens
Java requires that all classes used at runtime (e.g., AVL, AVLMenu, etc.) are already compiled into .class files. If you:
Pulled new code
Switched branches
Or edited files manually
...you must recompile before running the app.







