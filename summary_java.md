
# [CT0372-2] Lecture Notes for Java

[1] https://www.unive.it/data/insegnamento/361198

[2] Joshua Bloch, Effective Java Third Edition, Addison-Wesley Professional, 2017

[3] Kamalmeet Singh, Adrian Ianculescu, Lucian-Paul Torje, Design Patterns and Best Practices in Java, Packt Publishing Ltd, 2018

[4] Scott Meyers, Programmazione C++ Moderna, Hoepli, 2015


## Fundamental Constructs of Java

### Classes and interfaces
In Java, classes and interfaces are the fundamental building blocks of the language. A class is a blueprint for creating objects, which are instances of that class. It consists of data members (attributes) and methods (functions) that define the state and behavior of the objects. Interfaces, on the other hand, are a collection of abstract methods (i.e., method signatures without implementation) that serve as a contract for implementing classes.

Example:
```java
// Interface
public interface Drawable {
    void draw();
}

// Class implementing the interface
public class Circle implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
}
``` 
> In this example, we have an interface Drawable with an abstract method draw(). The class Circle implements this interface by providing an implementation for the draw() method. The Circle class also has an attribute radius and a constructor to initialize it.

### Subtype Polymorphism and Subsumption: Class Inheritance vs Interface Implementation
Polymorphism is a crucial concept in OOP that enables objects of different classes to be treated as objects of a common superclass or interface. Subtype polymorphism, also known as inclusion polymorphism, allows a subclass to inherit the properties and methods of its superclass, while subsumption refers to the ability of a subtype to be substituted wherever its supertype is expected.
#### Inheritance 
Inheritance is a mechanism in which one class acquires the properties and methods of another class. In Java, a class can inherit from only one superclass, leading to a single inheritance model. This approach helps avoid the diamond problem and simplifies the inheritance hierarchy.

Example:
```java
public class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public void printColor() {
        System.out.println("Color: " + color);
    }
}

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}
``` 
> In this example, the Rectangle class inherits properties and methods from the Shape class, like the color attribute and the printColor() method.
#### Interface Implementation
Interfaces in Java allow multiple inheritance, as a class can implement multiple interfaces. This approach is more flexible and promotes loose coupling between classes.

Example:
```java
public interface Movable {
    void move();
}

public interface Rotatable {
    void rotate();
}

public class Robot implements Movable, Rotatable {
    @Override
    public void move() {
        System.out.println("Moving forward.");
    }

    @Override
    public void rotate() {
        System.out.println("Rotating.");
    }
}
``` 
> In this example, the Robot class implements both the Movable and Rotatable interfaces, providing implementations for the move() and rotate() methods.
### Methods Overriding and Dynamic Dispatching
Method overriding is a feature in Java that allows a subclass to provide a new implementation for a method that is already defined in its superclass. This enables polymorphism and allows the subclass to inherit methods and properties from the superclass while customizing specific behavior.

#### Method Overriding Rules:
- The method in the subclass must have the same name and signature as the one in the superclass.
- The access level of the overriding method cannot be more restrictive than the overridden method.
- The return type must be the same or a subtype of the return type of the overridden method.
- If the superclass method declares any checked exceptions, the subclass method can only declare the same exceptions, their subtypes, or none at all.

Example:
```java
public class Animal {
    public void makeSound() {
        System.out.println("The animal makes a sound.");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("The dog barks.");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();
        myAnimal.makeSound(); // Output: The dog barks.
    }
}
``` 
> In this example, the Dog class overrides the makeSound() method from the Animal class. When we create an instance of Dog and assign it to an Animal reference, the overridden makeSound() method in the Dog class is called due to dynamic dispatching.

Dynamic dispatching is a mechanism used by object-oriented programming languages, like Java, to determine which method implementation to invoke at runtime. When you have a method overridden in a subclass, the Java runtime environment (JRE) chooses the appropriate method implementation based on the actual type of the object, rather than the type of the reference variable that holds the object.

Dynamic dispatching occurs automatically in Java and is a key aspect of polymorphism. It allows you to create more flexible and extensible code by enabling a single reference variable to hold objects of different types and automatically call the correct overridden method based on the object's actual type.

To further illustrate dynamic dispatching, let's extend the previous example:
```java
public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("The cat meows.");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal myAnimal1 = new Dog();
        Animal myAnimal2 = new Cat();

        myAnimal1.makeSound(); // Output: The dog barks.
        myAnimal2.makeSound(); // Output: The cat meows.
    }
}
``` 
> In this example, we have added a Cat class that also extends Animal and overrides the makeSound() method. In the main method, we create a Dog object and a Cat object, both assigned to Animal reference variables. When we call the makeSound() method on each reference, the JRE uses dynamic dispatching to determine which implementation to execute based on the actual object types (Dog and Cat), rather than the reference types (Animal).

### Overloading

Method overloading is a feature in Java that allows multiple methods with the same name but different parameter lists to be defined within the same class. Overloading allows you to create methods with the same functionality but varying input types, making your code more flexible and readable.

Static resolution (also known as compile-time resolution) is the process by which the Java compiler determines which overloaded method to invoke based on the reference types of the arguments passed at compile time.

#### Method Overloading Rules:

- Overloaded methods must have different parameter lists, either in the number of parameters or their types (or both).
- Overloaded methods can have different return types, but the return type alone is not enough to distinguish them.
- Access levels and other modifiers can be different for overloaded methods.

Example: 
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
``` 
>In this example, we have overloaded the add method in the Calculator class to handle different input types and a varying number of parameters.

#### Static Resolution
When calling an overloaded method, the Java compiler selects the most specific version of the method that is applicable to the provided arguments. If there is no unique version that is the most specific, a compile-time error occurs.

Example: 
```java
public class Test {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        int result1 = calc.add(1, 2); // Calls the add(int, int) method
        double result2 = calc.add(1.0, 2.0); // Calls the add(double, double) method
        int result3 = calc.add(1, 2, 3); // Calls the add(int, int, int) method
    }
}
``` 
In this example, the Java compiler resolves the overloaded add method calls based on the argument types.

### Nested, Static and Enclosing Class
Nested classes are classes defined within the scope of another class, known as the enclosing class. They are used to logically group classes that are only used in one place, to increase encapsulation, or to create more maintainable and organized code. Nested classes can be categorized into four types: static nested classes, non-static (inner) classes, local classes, and anonymous classes.

#### Static Nested Classes
A static nested class is a static member of the enclosing class and does not have access to the non-static members of the enclosing class. It can be instantiated without creating an instance of the enclosing class. Static nested classes are useful when you want to create a helper class that doesn't require access to the enclosing class's instance members or when you want to logically group a class with its outer class.

Example: 
```java
public class Outer {
    private static int staticVar = 42;

    public static class StaticNested {
        public void printStaticVar() {
            System.out.println("Static variable: " + staticVar);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer.StaticNested nested = new Outer.StaticNested();
        nested.printStaticVar(); // Output: Static variable: 42
    }
}
``` 
> In this example, the StaticNested class is a static nested class within the Outer class. It can access the staticVar variable of the Outer class and can be instantiated without creating an instance of the Outer class.

#### Non-static (Inner) Classes
An inner class is a non-static member of the enclosing class and has access to all the members (both static and non-static) of the enclosing class. An instance of an inner class cannot exist without an instance of the enclosing class. Inner classes are useful when you need a class that has access to the enclosing class's members and is closely associated with the outer class.

Example: 
```java
public class Outer {
    private int nonStaticVar = 42;

    public class Inner {
        public void printNonStaticVar() {
            System.out.println("Non-static variable: " + nonStaticVar);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.printNonStaticVar(); // Output: Non-static variable: 42
    }
}
``` 
> In this example, the Inner class is a non-static nested class within the Outer class. It can access the nonStaticVar variable of the Outer class, but it can only be instantiated through an existing instance of the Outer class.

#### Local Classes
A local class is defined within a method or a block of code, such as an if statement or a loop. Like inner classes, local classes have access to the members of their enclosing class. However, they can only access local variables and method parameters that are declared final or effectively final (i.e., their values don't change after assignment).

Example:
```java
public class Outer {
    private int nonStaticVar = 42;

    public void createLocalClass() {
        final int localVar = 99;

        class Local {
            public void printVars() {
                System.out.println("Non-static variable: " + nonStaticVar);
                System.out.println("Local variable: " + localVar);
            }
        }

        Local local = new Local();
        local.printVars();
    }
}
``` 
> In this example, the Local class is a local class defined within the createLocalClass method of the Outer class. It can access both the nonStaticVar member of the Outer class and the localVar defined within the method.

#### Anonymous Classes
Anonymous classes are unnamed classes declared and instantiated within an expression. They are typically used to create instances of classes that implement an interface or extend a class with only one or a few method implementations.

Example:
```java
public interface Drawable {
    void draw();
}

public class Test {
    public static void main(String[] args) {
        Drawable drawable = new Drawable() {
            @Override
            public void draw() {
                System.out.println("Drawing a shape.");
            }
        };

        drawable.draw(); // Output: Drawing a shape.
    }
}
``` 
In this example, we create an anonymous class that implements the Drawable interface and provides an implementation for the draw() method.

### Abstract Classes, Abstract Methods and Super constructors
Abstract classes, abstract methods, and super-constructors are essential components in Java's object-oriented programming. They facilitate the creation of flexible and reusable code.

#### Abstract Classes
An abstract class is a class that cannot be instantiated directly. Instead, it serves as a base class for other classes, defining common attributes and behavior. Abstract classes are useful when you want to provide a common interface or implementation for a group of related classes.

Example:
```java
public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double getArea();
}
``` 
> In this example, the Shape class is declared as abstract, providing a color attribute and a getColor() method. The getArea() method is also declared abstract, meaning that any concrete subclass of Shape must provide an implementation for this method.

#### Abstract Methods
Abstract methods are methods declared without an implementation in an abstract class. Subclasses of the abstract class must provide an implementation for these methods or be declared abstract themselves.

Example:
```java
public class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
``` 
> In this example, the Circle class extends the Shape abstract class and provides an implementation for the abstract getArea() method.

#### Super-Constructors
When a subclass constructor is called, it must call a constructor from the superclass (either implicitly or explicitly using super()). This ensures that the superclass's state is initialized correctly. The super() call must be the first statement in the subclass constructor.

Example:
```java
public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(String color, double length, double width) {
        super(color); // Calls the constructor of the superclass (Shape)
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }
}
``` 
> In this example, the Rectangle class extends the Shape abstract class. The constructor of Rectangle calls the super(color) constructor of the Shape class to initialize the color attribute inherited from the superclass.
### Exceptions
In Java, exceptions are used to handle abnormal conditions or errors that may occur during program execution. Exceptions can be broadly categorized into two types: checked exceptions and unchecked exceptions.

#### Checked Exceptions:
Checked exceptions are exceptions that the programmer is required to handle explicitly, either by catching the exception using a try-catch block or by declaring the exception in the method signature using the throws keyword. These exceptions usually represent recoverable errors or conditions outside the control of the program (e.g., I/O errors, network issues).

Example:
```java
public class FileReader {
    public static void main(String[] args) {
        try {
            File file = new File("example.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            System.out.println(line);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
``` 
> In this example, the FileReader and BufferedReader classes may throw FileNotFoundException and IOException checked exceptions, respectively. The programmer is required to handle these exceptions using a try-catch block.

#### Unchecked exceptions
 Unchecked exceptions are exceptions that the Java compiler does not require the programmer to handle explicitly. These exceptions usually represent programming errors or conditions that are typically unrecoverable (e.g., NullPointerException, IndexOutOfBoundsException). Unchecked exceptions extend the RuntimeException class, while checked exceptions extend the Exception class.
 
 Example:
 ```java
 public class Division {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;

        try {
            int result = a / b;
        } catch (ArithmeticException e) {
            System.err.println("Division by zero: " + e.getMessage());
        }
    }
}
``` 
> In this example, an ArithmeticException (an unchecked exception) may be thrown when dividing by zero. Although the programmer is not required to handle unchecked exceptions explicitly, it is still a good practice to handle them when the situation warrants it.

## Abstraction and polymorfism
Object-Oriented Programming (OOP) is a programming paradigm that uses objects and their interactions to design and implement software applications. It encourages modularity, code reuse, and abstraction. 

### Generics
Generics in Java provide a mechanism for creating classes, interfaces, and methods with type parameters, allowing programmers to create more reusable and type-safe code. They help eliminate the need for casting and runtime type errors. A generic type is defined using angle brackets (<>) and a placeholder for the type (usually "T" for "type").

For example, consider a simple generic class Box<T>:
 ```java
public class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
``` 
> Here, T is the type parameter, and it can be replaced with any reference type (e.g., Box<String> or Box<Integer>).
    
#### Parametric Polymorphism vs. Subtype Polymorphism

Parametric polymorphism allows a single function or class to operate on multiple types by using type variables, like generics in Java. In contrast, subtype polymorphism enables a function or class to work with instances of different classes related by inheritance or interface implementation.

For example, with parametric polymorphism, we can have a generic Stack<T> class that works with any type T. With subtype polymorphism, we can have a Shape interface with a draw() method, and multiple classes (e.g., Circle, Rectangle) implementing the Shape interface, each with their own draw() implementation.
    
#### Constraints on Generics:

Constraints on generics, also known as bounded type parameters, allow developers to restrict the types that can be used as type arguments for a generic type. For example, if you want to create a generic container for objects that are Comparable, you can use a bounded type parameter like this:
 ```java
public class SortedContainer<T extends Comparable<T>> {
    // ...
}
``` 
>This ensures that only types implementing Comparable can be used with the SortedContainer.
    
#### Wildcards with Upper/Lower Bounds:
Wildcards in generics provide more flexibility when using generic types. They use the ? symbol to represent an unknown type. Upper-bounded wildcards (using ? extends T) restrict the unknown type to be a subtype of T. Lower-bounded wildcards (using ? super T) restrict the unknown type to be a supertype of T.

For example, if you have a method that processes a list of Number objects or their subclasses (e.g., Integer, Double), you can use an upper-bounded wildcard like this:
 ```java
public void processNumbers(List<? extends Number> numbers) {
    // ...
}
``` 

### Anonymous Class
Anonymous classes are unnamed, single-use classes defined within the body of a method or an expression. They are typically used for implementing interfaces or extending classes with small amounts of custom behavior, without the need for creating a separate named class. Anonymous classes are a form of local classes and can capture variables from their enclosing scope, similar to lambda expressions.

To create an anonymous class, use the new keyword, followed by the interface or class being implemented/extended, and then provide the class body enclosed in curly braces {}. For example, consider a simple Runnable interface:
 ```java
public interface Runnable {
    void run();
}
``` 
An anonymous class implementing the Runnable interface can be created like this:
 ```java
Runnable myRunnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running...");
    }
};
``` 
While anonymous classes can be concise and useful for simple implementations, they can also lead to less-readable code when overused or when the implemented behavior is complex. In such cases, it may be better to use a named class or a lambda expression (for single-method interfaces).

Joshua Bloch's "Effective Java" (Third Edition) provides guidelines and best practices for using anonymous classes. Some key points include:

- Prefer lambda expressions over anonymous classes when implementing single-method interfaces (functional interfaces).
- Be aware of the increased memory footprint of anonymous classes compared to lambda expressions, as anonymous classes capture more information from their enclosing scope.
- Use anonymous classes judiciously to keep the code readable and maintainable.
Kamalmeet Singh et al.'s "Design Patterns and Best Practices in Java" discusses the use of anonymous classes in various design patterns, such as the Strategy pattern, where the behavior of an object can be modified at runtime by providing different implementations of an interface. In such cases, anonymous classes can be used to create concise, inline implementations without the need for separate named classes.

Example:

First, let's define a simple SortingStrategy interface
 ```java
public interface SortingStrategy {
    void sort(int[] array);
}
``` 
Now, let's create a Sorter class that can use different sorting strategies
 ```java
public class Sorter {
    private SortingStrategy strategy;

    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] array) {
        strategy.sort(array);
    }
}
``` 
Using anonymous classes, we can now create different implementations of the SortingStrategy interface and use them with the Sorter class
 ```java
public class Main {
    public static void main(String[] args) {
        int[] array = {5, 3, 1, 4, 2};

        // Using an anonymous class to implement a simple bubble sort strategy
        SortingStrategy bubbleSortStrategy = new SortingStrategy() {
            @Override
            public void sort(int[] array) {
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - 1 - i; j++) {
                        if (array[j] > array[j + 1]) {
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
            }
        };

        Sorter sorter = new Sorter(bubbleSortStrategy);
        sorter.sort(array);
        System.out.println(Arrays.toString(array)); // Output: [1, 2, 3, 4, 5]

        // Using an anonymous class to implement a reverse sort strategy
        SortingStrategy reverseSortStrategy = new SortingStrategy() {
            @Override
            public void sort(int[] array) {
                Arrays.sort(array);
                for (int i = 0; i < array.length / 2; i++) {
                    int temp = array[i];
                    array[i] = array[array.length - 1 - i];
                    array[array.length - 1 - i] = temp;
                }
            }
        };

        sorter.setStrategy(reverseSortStrategy);
        sorter.sort(array);
        System.out.println(Arrays.toString(array)); // Output: [5, 4, 3, 2, 1]
    }
}
``` 
> In this example, we've used anonymous classes to implement different sorting strategies for a Sorter object. This allows us to change the sorting behavior at runtime by simply providing a new implementation of the SortingStrategy interface.

### Lambda Expressions 
Lambda expressions, introduced in Java 8, are a concise way to represent instances of functional interfaces. They provide a more readable and compact alternative to anonymous classes for single-method interfaces, making it easier to work with functional programming concepts in Java. Lambda expressions can capture variables from their enclosing scope, just like anonymous classes.

Four forms of lambda expressions:

- No parameters and no return value: `() -> System.out.println("Hello World")`
- Single parameter with a return value: `x -> x * 2`
- Multiple parameters with a return value: `(x, y) -> x + y`
- Multiple parameters, a block of code, and a return value: `(x, y) -> { int z = x * y; return z; }`

#### Predefined Functional Interfaces in the JDK:

The Java Development Kit (JDK) provides a set of predefined functional interfaces in the java.util.function package. These interfaces represent common function types that can be used with lambda expressions, method references, and functional programming constructs. Some of the most commonly used predefined functional interfaces are:

- Function<T, R>: Represents a function that takes an argument of type T and returns a result of type R. Example: `Function<String, Integer> stringLength = s -> s.length();`
- Runnable: Represents a block of code with no arguments and no return value. Example: `Runnable printHello = () -> System.out.println("Hello");`
- Supplier<T>: Represents a function that takes no arguments and returns a result of type T. Example: `Supplier<String> stringSupplier = () -> "Hello, World!";`
- Consumer<T>: Represents a function that takes an argument of type T and returns no result. Example: `Consumer<String> printString = s -> System.out.println(s);`

#### Syntactic transformation of lambda expressions into anonymous classes:

Under the hood, the Java compiler transforms lambda expressions into instances of anonymous classes that implement the corresponding functional interface. For example, the following lambda expression:
 ```java
Runnable r = () -> System.out.println("Hello, World!");
``` 
Is transformed by the compiler into:
 ```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello, World!");
    }
};
``` 
#### Contravariance vs. Covariance with Wildcards in Function Domains and Codomains:

Contravariance and covariance refer to how the type relationships between generic parameters change when using wildcards. Contravariance occurs when a more general type is used as a parameter (using ? super T). Covariance occurs when a more specific type is used as a parameter (using ? extends T).

For a Function interface, you can use contravariance for the input type and covariance for the output type. For example:
```java
Function<? super Number, ? extends Number> func;
``` 
> Here, the input type can be any supertype of Number (contravariant), and the output type can be any subtype of Number (covariant).

Higher-Order Functions and Functional Programming:

Higher-order functions are functions that take other functions as arguments or return functions as results. They are a fundamental concept in functional programming, allowing for powerful abstractions and code reuse. In Java, you can use functional interfaces to represent higher-order functions. For example, a Function that takes another Function as an argument:
```java
Function<Function<Integer, Integer>, Function<Integer, Integer>> applyTwice = f -> x -> f.apply(f.apply(x));
``` 

#### map, filter, fold, iter, foreach:
These are common operations in functional programming. They allow you to manipulate and process collections of data in a declarative and concise way. In Java, you can use the Stream API to perform these operations on collections like lists and sets.

- map: Transform elements in a collection by applying a function. 
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> squaredNumbers = numbers.stream()
                                      .map(x -> x * x)
                                      .collect(Collectors.toList());
``` 
- filter: Keep only elements in a collection that satisfy a given condition.
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> evenNumbers = numbers.stream()
                                   .filter(x -> x % 2 == 0)
                                   .collect(Collectors.toList());
``` 
- fold (also called reduce): Aggregate elements in a collection by successively applying a function. 
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> sum = numbers.stream().reduce((x, y) -> x + y);
``` 
- iter (also called forEach): Iterate over elements in a collection and apply a function (usually for side effects).
```java
List<String> names = Arrays.asList("Alice", "Bob", "Carol");
names.stream().forEach(System.out::println);
``` 
## Multithreading and Concurrent Programming

Multithreading and concurrent programming are techniques that enable applications to perform multiple tasks simultaneously. This is achieved by using multiple threads of execution. In Java, the java.lang.Thread class and the java.util.concurrent package provide built-in support for multithreading and concurrent programming.

### Creating Threads

There are three common ways to create a new thread in Java:
- Using a lambda expression:
```java
Thread thread = new Thread(() -> {
    // Your code for the thread here
});
thread.start(); // Start the new thread
``` 
- Using an anonymous class:
```java
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        // Your code for the thread here
    }
});
thread.start(); // Start the new thread
```

- Using inheritance by extending the Thread class:
```java
class MyThread extends Thread {
    @Override
    public void run() {
        // Your code for the thread here
    }
}

MyThread thread = new MyThread();
thread.start(); // Start the new thread
```

### Thread Synchronization:
When multiple threads access shared resources, race conditions can occur, leading to unpredictable behavior. Java provides synchronization mechanisms to manage access to shared resources and ensure thread safety.

- synchronized keyword: The synchronized keyword is used to ensure that only one thread can access a shared resource at a time. It can be used with a method or a block of code.
```java
// Synchronized method
public synchronized void doSomething() {
    // Your code here
}

// Synchronized block
synchronized (object) {
    // Your code here
}
```
> When using a synchronized block, you need to provide an object to act as a lock. This object is referred to as the "monitor" or "monitor object."
When a thread enters a synchronized block, it acquires the lock on the monitor object. If another thread attempts to enter the synchronized block with the same monitor object, it must wait until the lock is released by the first thread. This ensures that only one thread can execute the critical section at a time, preventing race conditions and ensuring thread safety.  You can use any object as a monitor object, but it's a common practice to create a dedicated object for this purpose to avoid any unintended side effects.


- Lock interface: provides a more advanced and flexible locking mechanism than the synchronized keyword. It allows greater control over the locking process and better handling of contention. The most commonly used implementation of the Lock interface is the java.util.concurrent.locks.ReentrantLock class.Advantages of using Lock over synchronized include:
    - Flexibility: Lock allows you to attempt to acquire a lock without blocking (using tryLock()), or with a timeout (using tryLock(long, TimeUnit)), giving you more control over the locking process.
    - Interruptibility: Lock supports the ability to interrupt a thread waiting to acquire a lock, which can be useful in cases of deadlock or when a higher-priority task needs to be executed.
    - Read/Write Locks: The java.util.concurrent.locks package provides a ReadWriteLock interface, which allows multiple readers to access a shared resource simultaneously while preventing concurrent access by writers. This can improve performance in read-heavy scenarios.
```java
Lock lock = new ReentrantLock();

lock.lock(); // Acquire the lock
try {
    // Your code here
} finally {
    lock.unlock(); // Release the lock
}
```
- Semaphore class: A semaphore is a synchronization construct that manages access to a shared resource using a counter. It is useful in scenarios where you want to control the number of threads that can access a resource concurrently. The java.util.concurrent.Semaphore class provides an implementation of a semaphore. A semaphore maintains a set of permits. When a thread wants to access the shared resource, it must acquire a permit from the semaphore. If no permits are available, the thread will block until a permit is released by another thread. When the thread is done with the resource, it releases the permit back to the semaphore.

```java
int permits = 3; // Allow up to 3 threads to access the shared resource concurrently
Semaphore semaphore = new Semaphore(permits);

semaphore.acquire(); // Acquire a permit from the semaphore
try {
    // Your code accessing shared resource here
} finally {
    semaphore.release(); // Release the permit back to the semaphore
}
```

### Thread Communication:

Threads often need to communicate with each other to coordinate their actions. Java provides several mechanisms for inter-thread communication:

- wait() and notify() methods: These methods are used with the synchronized keyword to manage access to shared resources. wait() releases the lock on the shared resource and causes the current thread to wait until another thread calls notify() or notifyAll() on the same object. notify() wakes up a single waiting thread, while notifyAll() wakes up all waiting threads.

```java
synchronized (object) {
    while (!condition) {
        object.wait();
    }
    // Your code here
}

synchronized (object) {
    // Change the condition
    object.notify(); // or object.notifyAll();
}
```
- BlockingQueue interface: The java.util.concurrent.BlockingQueue interface is a type of queue that provides thread-safe operations for adding, removing, and examining elements in the queue. It extends the java.util.Queue interface and adds support for blocking operations, which means that threads trying to insert an element in a full queue or remove an element from an empty queue will block until space becomes available or an element is added, respectively. BlockingQueue implementations are commonly used for communication between producer and consumer threads in a multi-threaded environment, as they handle synchronization, resource management, and thread communication implicitly. Some popular BlockingQueue implementations in Java include:
    - ArrayBlockingQueue: A bounded blocking queue backed by an array.
    - LinkedBlockingQueue: An optionally-bounded blocking queue based on linked nodes.
    - PriorityBlockingQueue: An unbounded blocking queue that uses a priority heap to order elements according to their natural order or a provided comparator.
```java
BlockingQueue<String> queue = new LinkedBlockingQueue<>();

// Producer thread
new Thread(() -> {
    try {
        queue.put("message");
    } catch (InterruptedException e) {
        // Handle the exception
    }
}).start();

// Consumer thread
new Thread(() -> {
    try {
        String message = queue.take();
    } catch (InterruptedException e) {
        // Handle the exception
    }
}).start();
```
> In this example, the producer thread puts an item into the queue, and the consumer thread takes the item from the queue. The put() and take() methods block when the queue is full or empty, respectively, ensuring that the threads coordinate their actions.

- CountDownLatch class: The java.util.concurrent.CountDownLatch class is a synchronization construct that enables one or more threads to wait for a set of events to occur. It is initialized with a count, which represents the number of events that must occur before the latch is released. The latch is released when the count reaches zero. CountDownLatch is useful in scenarios where you want to wait for multiple threads to complete their tasks before proceeding. For example, you might use a CountDownLatch to wait for all worker threads to finish processing data before aggregating the results.
```java
int numberOfWorkers = 5;
CountDownLatch latch = new CountDownLatch(numberOfWorkers);

// Start worker threads
for (int i = 0; i < numberOfWorkers; i++) {
    new Thread(() -> {
        // Perform work
        // ...
        
        latch.countDown(); // Decrement the latch count
    }).start();
}

// Main thread waits for all worker threads to finish
try {
    latch.await();
} catch (InterruptedException e) {
    // Handle the exception
}

// Continue with the rest of the program
// ...
```
> In this example, the CountDownLatch is initialized with a count equal to the number of worker threads. Each worker thread decrements the count when it finishes its work. The main thread waits for the latch to be released (i.e., the count to reach zero) before proceeding to the next part of the program.

### The try-with-resources construct:
Java 7 introduced the try-with-resources construct, which is designed to simplify the management of resources that implement the AutoCloseable interface, such as files, sockets, and database connections. This feature helps to ensure that resources are closed automatically when they are no longer needed, reducing the risk of resource leaks and making the code more readable and maintainable.

The try-with-resources statement requires resources to be declared within parentheses immediately following the try keyword. These resources are automatically closed when the try block is exited, regardless of whether an exception is thrown or not. This automatic closing eliminates the need for a finally block to explicitly close the resources.

Here's an example of using the try-with-resources construct with a FileInputStream and a FileOutputStream:
```java
try (FileInputStream fis = new FileInputStream("input.txt");
     FileOutputStream fos = new FileOutputStream("output.txt")) {
    // Your code to read from input.txt and write to output.txt
} catch (IOException e) {
    // Handle the exception
}
```
> In this example, both FileInputStream and FileOutputStream implement the AutoCloseable interface. As a result, they are automatically closed when the try block is exited. The try-with-resources construct reduces the amount of boilerplate code required to manage resources and ensures that they are closed properly, even in the case of exceptions.

## Design Pattern

Design patterns are reusable solutions to common problems that arise in software design. They provide a shared vocabulary and best practices for solving specific issues, making it easier for developers to communicate and collaborate. Design patterns are not one-size-fits-all solutions but serve as a template that can be adapted to fit the requirements of a particular problem.

### Command Pattern:

The Command pattern is a behavioral design pattern that encapsulates a request as an object, allowing you to parameterize clients with different requests, queue or log requests, and support undoable operations. It separates the object that invokes the operation from the object that actually performs it, promoting loose coupling and flexibility.

Here's an example of the Command pattern in Java:
```java
// Command interface
public interface Command {
    void execute();
}

// Concrete Command classes
class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Receiver class
class Light {
    public void turnOn() {
        System.out.println("The light is on.");
    }

    public void turnOff() {
        System.out.println("The light is off.");
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(new TurnOnLightCommand(light));
        remoteControl.pressButton(); // Output: The light is on.

        remoteControl.setCommand(new TurnOffLightCommand(light));
        remoteControl.pressButton(); // Output: The light is off.
    }
}
```
#### Hybrid Functional Programming:

Hybrid functional programming is a programming paradigm that combines elements of functional programming with other paradigms, such as object-oriented programming. Java, since version 8, has embraced some functional programming concepts, like lambda expressions, functional interfaces, and the Stream API, allowing developers to write more expressive and concise code.

#### Functional Interface, Anonymous Class, Lambda, and Callback:

Functional interfaces, anonymous classes, lambda expressions, and callbacks are related concepts in Java that enable more flexible and expressive code.

- Functional Interface: An interface with a single abstract method (known as a SAM - Single Abstract Method). Functional interfaces can be used as targets for lambda expressions or method references. Examples include Runnable, Callable, and Comparator.

- Anonymous Class: A class without a name that is defined and instantiated at the same time. Anonymous classes are often used for implementing functional interfaces or callbacks when there is no need to reuse the implementation.

- Lambda Expression: A short and concise way to represent a functional interface implementation. Lambda expressions can be assigned to functional interface variables or passed as arguments to methods.

- Callback: A mechanism that allows a piece of code to be executed at a later time or in response to an event. Callbacks are often implemented using functional interfaces, anonymous classes, or lambda expressions.

Here's an example that demonstrates the use of functional interfaces, anonymous classes, and lambda expressions in a callback:
```java
// Functional Interface
interface Callback {
    void onSuccess(String result);
}

// A class that simulates an asynchronous operation
class AsyncTask {
    public void execute(Callback callback) {
        // Simulate an asynchronous operation
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate a delay
                callback.onSuccess("Operation completed successfully.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

public class Main {
    public static void main(String[] args) {
        AsyncTask task = new AsyncTask();

        // Using an anonymous class for the callback
        task.execute(new Callback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Anonymous class: " + result);
            }
        });

        // Using a lambda expression for the callback
        task.execute(result -> System.out.println("Lambda: " + result));
    }
}
```
> In this example, we have an AsyncTask class that simulates an asynchronous operation. The execute method of the AsyncTask class takes a Callback functional interface as an argument. When the asynchronous operation is completed, the onSuccess method of the Callback is called with the result. In the main method, we create an instance of the AsyncTask class and call the execute method twice: once using an anonymous class for the callback, and once using a lambda expression. Both callbacks simply print the result.

### Iterator
Iterators are a design pattern used to traverse through elements in a collection without exposing the underlying representation. In Java, the Iterator interface provides methods like hasNext(), next(), and remove() for iterating over collections.

#### Collection, List, Map, and JDK's Generic Containers:

Java's Collections Framework provides a wide range of generic container classes for storing, organizing, and manipulating data. These include:

- Collection: The root interface for most of the collections in Java. It defines basic methods like add(), remove(), size(), and contains() that are common to all collections.

- List: An ordered collection that allows duplicates. Examples include ArrayList, LinkedList, and Vector.

- Map: An associative collection that maps keys to values. It doesn't inherit from the Collection interface but is still a part of the Collections Framework. Examples include HashMap, TreeMap, and LinkedHashMap.

#### The Generic Iterable Interface as a Supertype of Containers:

The Iterable interface is a generic interface that serves as the root for all container types that can be iterated over. It has a single method, iterator(), which returns an instance of Iterator. The Collection interface extends Iterable, making all collection classes in Java iterable by default.

#### Iterators vs Higher-Order Functions (map/filter/fold):

While iterators provide a simple way to traverse collections, higher-order functions like map, filter, and fold (also known as reduce) offer a more functional approach to manipulate collections. They allow developers to perform operations on collections in a declarative and concise manner.

| Iterators      | Higher-Order Functions |
| ----------- | ----------- |
| Require imperative code and manual management of iteration. They can be error-prone, as developers must handle edge cases and maintain the iteration state.      | Encapsulate common operations like mapping, filtering, and folding in reusable functions. They lead to more concise and expressive code, as the operations are abstracted away from the specific details of the iteration.       |


Java's Stream API allows developers to work with collections using higher-order functions. For instance, the following example demonstrates using the Stream API to filter a list of integers and find their sum:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
int sum = numbers.stream()
                 .filter(n -> n % 2 == 0)
                 .mapToInt(Integer::intValue)
                 .sum();
```
### Singleton:

The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance. It is useful when you need to control access to shared resources or centralize the management of certain objects.

In Java, there are several ways to implement a Singleton:

- Lazy Initialization (non-thread-safe):
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
- Thread-safe Singleton using Synchronization
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
- Bill Pugh's Initialization-on-demand Holder idiom (thread-safe):
```java
public class Singleton {
    private Singleton() {}

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```
- Enum Singleton (thread-safe):
```java
public enum Singleton {
    INSTANCE;

    // Add other methods or fields as needed
}
```

### Consumer-Producer:

The Consumer-Producer pattern is a concurrency pattern where one or more threads (producers) create items and put them in a shared data structure, while other threads (consumers) remove and process those items. It is a classic example of multi-threading and synchronization.

#### Multi-threading and Synchronization on a Blocking Queue:

A common implementation of the Consumer-Producer pattern is using a blocking queue. The java.util.concurrent package provides a BlockingQueue interface that can be used for this purpose. The ArrayBlockingQueue and LinkedBlockingQueue classes are common implementations of the BlockingQueue interface.

Here is an example of a simple multi-threaded Consumer-Producer using a BlockingQueue:
```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsumerProducer {
    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(new Producer(sharedQueue));
        Thread consumer = new Thread(new Consumer(sharedQueue));

        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;

    public Producer(BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Producing: " + i);
                sharedQueue.put(i);
                Thread.sleep(500);
            } catch (Interrupted e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;

    public Consumer(BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int item = sharedQueue.take();
                System.out.println("Consuming: " + item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```
