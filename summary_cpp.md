# [CT0372-2] Lecture Notes for C++

[1] https://www.unive.it/data/insegnamento/361198

[2] Joshua Bloch, Effective Java Third Edition, Addison-Wesley Professional, 2017

[3] Kamalmeet Singh, Adrian Ianculescu, Lucian-Paul Torje, Design Patterns and Best Practices in Java, Packt Publishing Ltd, 2018

[4] Scott Meyers, Programmazione C++ Moderna, Hoepli, 2015


## Passing argument by value (copy) vs reference
In C++, when a function is called, the arguments can be passed either by value (copy) or by reference.

- Passing by Value (Copy): When passing by value, a copy of the original variable is created and passed to the function. This means that any changes made to the variable within the function will not affect the original variable. This method ensures data protection but may lead to performance issues when passing large objects.
Example:
```cpp
void by_value(int x) {
    x = x + 1;
}

int main() {
    int a = 5;
    by_value(a);
    std::cout << a << std::endl; // Output: 5 (unchanged)
}
``` 
- Passing by Reference: When passing by reference, a reference (or alias) to the original variable is passed to the function. This means that any changes made to the variable within the function will affect the original variable. This method is more efficient when dealing with large objects but can lead to unintended side effects.
Example:
```cpp
void by_reference(int& x) {
    x = x + 1;
}

int main() {
    int a = 5;
    by_reference(a);
    std::cout << a << std::endl; // Output: 6 (changed)
}
``` 
### Const-correctness
Const-correctness is a programming practice in C++ that enforces the proper use of the 'const' keyword to indicate that an object or variable should not be modified. This helps to ensure the safety and reliability of the code, as it prevents unintentional modifications to the data.

- Using const with functions: When declaring a member function as 'const', it means that the function will not modify the state of the object it belongs to. This allows for better code readability and avoids unintended side effects.

```cpp
class MyClass {
public:
    int get_value() const {
        return value;
    }

private:
    int value;
};
``` 
- Using const with pointers and references: When declaring a pointer or reference as 'const', it means that the object being pointed to or referenced cannot be modified. This can be helpful when passing data to functions, ensuring that the data remains unaltered.
```cpp
void print_value(const int& x) {
    std::cout << x << std::endl;
    // x = 5; // Error: cannot modify a const reference
}

int main() {
    int a = 3;
    print_value(a);
}
``` 



## Value-Oriented Style: Instantiating Objects on the Stack
In C++, objects can be instantiated on the stack, which offers faster allocation and deallocation compared to heap allocation. Stack-allocated objects have automatic storage duration, meaning they are created and destroyed within the scope in which they are defined.
```cpp
void create_object() {
    MyClass obj; // Stack-allocated object
}
``` 
### Constructors 

A constructor is a special member function that initializes an object's data members. Constructors have the same name as the class and are invoked automatically when an object is created.

- Default Constructor: A constructor that takes no arguments. If no constructor is provided, the compiler generates a default constructor that initializes the object with default values.
```cpp
class MyClass {
public:
    MyClass() : value(0) {} // Default constructor

private:
    int value;
};
``` 
- Copy Constructor: A constructor that initializes an object by copying the data from another object of the same class. The copy constructor takes a reference to a const object of the same class as its argument.
```cpp
class MyClass {
public:
    MyClass(const MyClass& other) : value(other.value) {} // Copy constructor

private:
    int value;
};
``` 
- Field Initialization Syntax: Constructors can use member initializer lists to initialize data members before the constructor body is executed. This is more efficient and can help avoid certain issues, such as initializing const or reference members.
```cpp
class MyClass {
public:
    MyClass(int val) : value(val) {} // Constructor with member initializer list

private:
    int value;
};
``` 
- Unary Conversion Constructors vs. Explicit: Unary constructors are constructors that can be called with a single argument. They implicitly define a conversion from the argument type to the class type. To prevent unintended conversions, the 'explicit' keyword can be used.
```cpp
class MyClass {
public:
    explicit MyClass(int val) : value(val) {} // Explicit constructor

private:
    int value;
};
``` 
- Templated Copy-Conversion Constructors: These constructors allow for the creation of objects from different types using template-based conversion. This is useful when implementing generic classes or functions that work with various types.
```cpp
template<typename T>
class MyClass {
public:
    template<typename U>
    MyClass(const MyClass<U>& other) : value(other.get_value()) {} // Templated copy-conversion constructor

    T get_value() const {
        return value;
    }

private:
    T value;
};
``` 
### Assignment Operator
In C++, the value-oriented style focuses on treating objects as values and ensuring that operations on them behave as expected. One essential aspect of the value-oriented style is the proper implementation of the assignment operator.

The assignment operator (=) is used to assign the value of one object to another. By default, the assignment operator performs a shallow copy, which means it copies the values of the data members from one object to another. However, when dealing with dynamically allocated resources, a shallow copy can lead to issues such as double deletion or memory leaks.

To avoid these issues, it is necessary to implement a custom assignment operator, which performs a deep copy of the resources. This is called the copy-assignment operator and has the following signature:
```cpp
class_name& operator=(const class_name&);
``` 
Here's an example of implementing a custom assignment operator for a class that manages a dynamically allocated resource:
```cpp
class MyClass {
public:
    // Constructor
    MyClass(int size) : size(size), data(new int[size]) {}

    // Destructor
    ~MyClass() {
        delete[] data;
    }

    // Copy constructor
    MyClass(const MyClass& other) : size(other.size), data(new int[size]) {
        std::copy(other.data, other.data + size, data);
    }

    // Copy-assignment operator
    MyClass& operator=(const MyClass& other) {
        if (this == &other) {
            return *this; // Self-assignment protection
        }

        // Release current resources
        delete[] data;

        // Copy data from the other object
        size = other.size;
        data = new int[size];
        std::copy(other.data, other.data + size, data);

        return *this;
    }

private:
    int size;
    int* data;
};
```
> In this example, the custom assignment operator performs the following steps:
> - Check for self-assignment: If the source and destination objects are the same, there's no need to perform the assignment.
> - Release the current resources: The destination object should free its existing resources to avoid memory leaks.
> - Copy data from the source object: Allocate new resources and copy the data from the source object to the destination object.
> - Return a reference to the destination object: This allows for chained assignment (e.g., a = b = c).
> 
> The custom assignment operator ensures that resources are managed correctly, and the value-oriented style is maintained.

### Operator Overloading and Methods
In C++, the value-oriented style encourages treating objects as values and ensuring that their behavior is intuitive and consistent. A key aspect of this approach is overloading operators and implementing methods that provide a seamless and natural interface for working with objects.

- Operator Overloading: Operator overloading allows you to define custom behaviors for built-in operators when used with user-defined types. This feature makes it possible to use familiar syntax and semantics with custom objects, leading to more readable and expressive code.

Example: Implementing addition and stream insertion operators for a custom Complex class:
```cpp
class Complex {
public:
    Complex(double real, double imaginary) : real(real), imaginary(imaginary) {}

    // Overload the addition operator
    Complex operator+(const Complex& other) const {
        return Complex(real + other.real, imaginary + other.imaginary);
    }

    // Overload the stream insertion operator
    friend std::ostream& operator<<(std::ostream& os, const Complex& c) {
        os << c.real << " + " << c.imaginary << "i";
        return os;
    }

private:
    double real;
    double imaginary;
};

int main() {
    Complex a(1, 2);
    Complex b(3, 4);
    Complex c = a + b;

    std::cout << c << std::endl; // Output: 4 + 6i
}
```

- Methods: In addition to operator overloading, implementing methods that provide a natural and consistent interface for working with objects is essential in the value-oriented style. These methods should follow best practices, such as the rule of three/five (for resource management) and using const-correctness.

Example: Adding a magnitude method to the Complex class:
```cpp
class Complex {
public:
    // ... (previous code)

    // Calculate the magnitude of the complex number
    double magnitude() const {
        return std::sqrt(real * real + imaginary * imaginary);
    }

    // ... (previous code)
};

int main() {
    Complex a(3, 4);
    std::cout << "Magnitude: " << a.magnitude() << std::endl; // Output: Magnitude: 5
}
```
### Destructors
In the value-oriented style, proper resource management is crucial for ensuring that objects behave like values. Destructors play a vital role in managing resources, as they are responsible for cleaning up and releasing any resources an object holds when it goes out of scope or is explicitly deleted.

A destructor is a special member function of a class that is executed whenever an object of the class is destroyed. The destructor's name is the same as the class name, preceded by a tilde (~). A class can have only one destructor, and it cannot have any arguments or return values.

When implementing a destructor, it is important to ensure that all dynamically allocated resources are properly deallocated, and any other cleanup tasks are performed. This is especially relevant when working with the rule of three or the rule of five, which dictate that if a class manages resources, it should provide a destructor, a copy constructor, and a copy-assignment operator (rule of three), or additionally, a move constructor and a move-assignment operator (rule of five).

Example: Implementing a destructor for a custom String class that manages a dynamically allocated character array:
```cpp
class String {
public:
    // Constructor
    String(const char* str) {
        length = std::strlen(str);
        data = new char[length + 1];
        std::strcpy(data, str);
    }

    // Destructor
    ~String() {
        delete[] data;
    }

    // ... (other methods and operators)

private:
    char* data;
    std::size_t length;
};

int main() {
    {
        String s("Hello, world!");
        // ... (use the string)
    } // s goes out of scope, the destructor is called, and the memory is freed
}
```
> In this example, the destructor releases the dynamically allocated memory held by the String object, ensuring no memory leaks occur. The proper implementation of destructors is essential for maintaining the value-oriented style in C++.


### Lvalues and Rvalues Properties

In the value-oriented style, understanding the properties of lvalues and rvalues is important for writing efficient and expressive code. Lvalues and rvalues are categories of expressions in C++ that determine how objects are accessed, assigned, and moved.

- Lvalues: Lvalues are expressions that represent a named, addressable location in memory. They can appear on the left-hand side of an assignment operator. Lvalues are typically used for accessing and modifying the value of an existing object.

Example:
```cpp
int x = 5; // x is an lvalue
int& y = x; // y is an lvalue reference to x
y = 10; // modifying the value of x through the lvalue reference y
```

- Rvalues: Rvalues are temporary or anonymous values that result from an expression and do not have a named location in memory. They can appear on the right-hand side of an assignment operator. Rvalues are typically used for moving or copying the value of an object to another object.
Example:
```cpp
int x = 5 + 3; // 5 + 3 is an rvalue
int&& y = 7 * 4; // y is an rvalue reference to the temporary value 7 * 4
```
In modern C++, understanding lvalues and rvalues is crucial for the efficient use of resources, especially when dealing with move semantics. Move semantics allow you to transfer the ownership of a resource (e.g., dynamically allocated memory) from one object to another, avoiding expensive copy operations.

Example: Implementing move constructor and move-assignment operator for the custom String class:
```cpp
class String {
public:
    // ... (previous code)

    // Move constructor
    String(String&& other) noexcept : data(other.data), length(other.length) {
        other.data = nullptr;
        other.length = 0;
    }

    // Move-assignment operator
    String& operator=(String&& other) noexcept {
        if (this != &other) {
            delete[] data;

            data = other.data;
            length = other.length;

            other.data = nullptr;
            other.length = 0;
        }

        return *this;
    }

    // ... (previous code)
};

int main() {
    String s1("Hello, world!");
    String s2(std::move(s1)); // move constructor is called
    String s3("Goodbye, world!");
    s3 = std::move(s2); // move-assignment operator is called
}
```


#### Const Overloading on this

In C++, the value-oriented style emphasizes the importance of const-correctness, which means ensuring that functions do not modify objects when they are not supposed to. One technique to achieve this is const overloading on the this pointer, which allows you to provide different implementations of a member function depending on whether the object is const or non-const.

Const overloading involves declaring two versions of a member function: one marked as const and the other not. The const version is called on const objects, while the non-const version is called on non-const objects.

Here's an example of const overloading for a custom Array class:
```cpp
Copy code
class Array {
public:
    Array(int size) : size(size), data(new int[size]) {}

    // Non-const version of the subscript operator
    int& operator[](int index) {
        std::cout << "Non-const version called" << std::endl;
        return data[index];
    }

    // Const version of the subscript operator
    const int& operator[](int index) const {
        std::cout << "Const version called" << std::endl;
        return data[index];
    }

private:
    int size;
    int* data;
};

int main() {
    Array arr(5);

    int& value1 = arr[0]; // Non-const version called
    const Array& constArr = arr;
    const int& value2 = constArr[0]; // Const version called
}




```
> In this example, the Array class provides two overloads of the subscript operator: one for non-const objects and another for const objects. The non-const version allows the user to modify the array elements, while the const version only allows read access.

## generic programming (GP)
Generic programming is a programming paradigm that focuses on creating software components which can be reused across different data types and algorithms. The main goal is to write code that is flexible, reusable, and efficient. In C++, the template system is used to achieve generic programming by allowing the creation of classes and functions that can work with different data types without the need to specify them explicitly.
### Template System 
The template system in C++ allows for the creation of generic classes and functions which can work with a variety of data types. This is achieved through the use of type parameters, also known as parametric types.
#### Template Classes
A template class is a class that is parameterized by one or more types. This allows the class to work with different data types without the need for explicit specialization. To define a template class, the 'template' keyword is used, followed by the type parameters in angle brackets.
```cpp
template<typename T>
class MyClass {
    T data;
public:
    void setData(const T& value) {
        data = value;
    }
    T getData() const {
        return data;
    }
};
```
#### Template Functions/Methods:
Template functions, also known as parametric polymorphism (polimorfismo parametrico), are functions that can work with different data types without the need for explicit specialization. They are defined using the 'template' keyword, followed by the type parameters in angle brackets.
```cpp
template<typename T>
T getMax(const T& a, const T& b) {
    return (a > b) ? a : b;
}
```
- Type Deduction: In most cases, the C++ compiler can deduce the template argument types based on the provided arguments. This process, known as type deduction, simplifies the usage of template classes and functions.
```cpp
MyClass<int> myIntObj; // Explicitly specifying the type
MyClass myAutoIntObj = MyClass<int>(); // Type deduction

int maxVal = getMax<int>(5, 10); // Explicitly specifying the type
int autoMaxVal = getMax(5, 10); // Type deduction
```  
- Template Specialization: Template specialization allows for custom implementation of a template class or function for specific data types or conditions. There are two types of specialization: full specialization and partial specialization.

- Full Specialization: Full specialization involves providing a custom implementation for a specific data type.
```cpp
    template<>
class MyClass<bool> {
    // Custom implementation for bool type
};
```  
    
- Partial Specialization: Partial specialization involves providing a custom implementation for a subset of data types or conditions.
```cpp  
    template<typename T>
class MyClass<T*> {
    // Custom implementation for pointer types
};
 ```     
    
- Variadic Templates: Variadic templates allow for template classes and functions with a variable number of type parameters.
 ```cpp     
    template<typename... Ts>
class MyClass {
    // Implementation for variadic template class
};

template<typename T, typename... Ts>
void myFunction(T first, Ts... rest) {
    // Implementation for variadic template function
}
  ```    
    
    
#### Parametric Polymorphism
Parametric polymorphism (polimorfismo parametrico) refers to the ability of a function or method to operate on multiple data types without having to modify its implementation. This is achieved by using template functions or methods.
 ```cpp   
template<typename T>
void print(const T& value) {
    std::cout << value << std::endl;
}

print<int>(42); // works for int
print<std::string>("Hello, world!"); // works for std::string
  ```  
####  Static Dispatching and Overloading Resolution:
Static dispatching is the process of determining which function or method to call at compile time, based on the provided arguments. In the context of templates, static dispatching is essential for choosing the correct specialization or instantiation of a template function or method. Overloading resolution is the process of selecting the most appropriate function or method among several overloaded versions based on the provided arguments.
 ```cpp 
template<typename T>
void foo(const T& value) {
    std::cout << "Generic version" << std::endl;
}

void foo(const int& value) {
    std::cout << "Overloaded version for int" << std::endl;
}

foo(42); // Calls the overloaded version for int
foo(3.14); // Calls the generic version
  ```
#### Generative Template System and Delayed Type Checking:
The generative template system in C++ enables the creation of specific instances of template classes and functions at compile time, depending on the types used. Delayed type checking refers to the fact that the compiler checks the validity of the template code when it is instantiated, rather than when it is defined. This allows for more flexibility in template code, as errors are only reported if they are encountered during instantiation with specific types.
 ```cpp 
 template<typename T>
class MyClass {
    T data;

public:
    void setData(const T& value) {
        data = value;
        data.nonExistentMethod(); // No error reported during definition
    }
};

MyClass<int> obj; // Error reported during instantiation: int has no nonExistentMethod
  ```
### member types
Member types are nested types defined within a class or struct, and can also be used as type parameters in template classes or functions. This enables more flexible and modular code design.
 ```cpp 
 template<typename T>
class Container {
public:
    using ValueType = T;
    using PointerType = T*;
    using ReferenceType = T&;
};
  ```
#### Templating Containers and Iterators:
Templating is widely used in the design of container classes and iterators in the C++ Standard Library. This allows for the creation of generic containers that can store any type of data, and generic iterators that can traverse any container type.
 ```cpp 
// A simple example of a templated container and iterator
template<typename T>
class SimpleVector {
    std::vector<T> data;

public:
    using iterator = typename std::vector<T>::iterator;

    void push_back(const T& value) {
        data.push_back(value);
    }

    iterator begin() {
        return data.begin();
    }

    iterator end() {
        return data.end();
    }
};
  ```
#### type traits
Type traits are a set of template classes that provide information about types at compile time. They can be used to perform compile-time type checking, type manipulation, and enable specialized behavior based on type properties. The C++ Standard Library provides a collection of type traits in the <type_traits> header.
 ```cpp 
#include <type_traits>

template<typename T>
void printIfIntegral(const T& value) {
    if constexpr (std::is_integral_v<T>) {
        std::cout << value << std::endl;
    } else {
        std::cout << "Not an integral type" << std::endl;
    }
}

printIfIntegral(42); // Output: 42
printIfIntegral(3.14); // Output: Not an integral type
  ```
  
  
## object-oriented programming (OOP)
### Inheritance: Private, Protected, and Public Base Classes:
nheritance is a key concept in object-oriented programming that enables a new class (derived class) to inherit the properties and methods of an existing class (base class). In C++, inheritance can be private, protected, or public, which determines the accessibility of the base class members in the derived class.

- Private Inheritance: With private inheritance, the public and protected members of the base class become private members of the derived class. This type of inheritance is less common and is mainly used when the derived class wants to hide the implementation details of the base class.
```cpp 
class Base {
public:
    void publicMethod();
protected:
    void protectedMethod();
};

class Derived : private Base {
    // publicMethod and protectedMethod are now private members of Derived
};
  ```
- Protected Inheritance: With protected inheritance, the public and protected members of the base class become protected members of the derived class. This type of inheritance is also less common and is used when the derived class wants to restrict access to the base class members to itself and its subclasses.
 ```cpp 
class Base {
public:
    void publicMethod();
protected:
    void protectedMethod();
};

class Derived : protected Base {
    // publicMethod and protectedMethod are now protected members of Derived
};
  ```
- Public Inheritance: Public inheritance is the most common form of inheritance in C++. With public inheritance, the public members of the base class remain public in the derived class, and the protected members of the base class remain protected in the derived class. This type of inheritance is used when the derived class wants to maintain the same interface as the base class and extend its functionality.
 ```cpp 
class Base {
public:
    void publicMethod();
protected:
    void protectedMethod();
};

class Derived : public Base {
    // publicMethod remains public, protectedMethod remains protected
};
  ```
### Subsumption between Pointers and References:
Subsumption is a concept in C++ that refers to the relationship between pointers and references. Understanding the differences and similarities between pointers and references can help programmers make more informed decisions about which to use in specific situations.

#### Pointers
Pointers are variables that store the memory address of another variable. They can be dereferenced to access the value stored at the memory address they point to. Pointers can be reassigned to point to different memory addresses, and they can also be null, which means they point to no memory address at all.
 ```cpp 
int a = 42;
int *ptr = &a; // ptr points to the memory address of a

*ptr = 100; // Dereferencing the pointer to modify the value of a
std::cout << a << std::endl; // Output: 100

ptr = nullptr; // ptr is now a null pointer
  ```
  
#### References:
References are aliases for another variable, providing an alternative name for the same memory location. Unlike pointers, references cannot be reassigned to refer to another variable, and they cannot be null.
 ```cpp 
int a = 42;
int &ref = a; // ref is an alias for a

ref = 100; // Modifying the value of a through the reference
std::cout << a << std::endl; // Output: 100
  ```
  
#### Subsumption between Pointers and References:
There are several key differences between pointers and references:

- Pointers can be reassigned to point to different memory addresses, while references cannot be reassigned.
- Pointers can be null, while references must always refer to a valid memory location.
- Pointers require dereferencing to access the value they point to, while references can be used directly as an alias for the variable they refer to.
- Despite these differences, pointers and references can sometimes be used interchangeably, depending on the use case. For instance, both can be used as function arguments to pass variables by reference, allowing functions to modify the original value.

 ```cpp 
void modifyByPointer(int *ptr) {
    *ptr = 100;
}

void modifyByReference(int &ref) {
    ref = 100;
}

int a = 42;
modifyByPointer(&a); // Pass by pointer
std::cout << a << std::endl; // Output: 100

a = 42;
modifyByReference(a); // Pass by reference
std::cout << a << std::endl; // Output: 100

  ```
### Virtual Methods and Override:
Virtual methods are an important aspect of object-oriented programming, enabling polymorphism, which allows a derived class to provide a different implementation of a method defined in the base class. The virtual keyword is used to declare a method as virtual in the base class.

#### Virtual Methods:

When a method is declared as virtual in a base class, a derived class can provide its own implementation of the method. This allows the derived class to override the behavior of the base class without changing the interface.
 ```cpp 
class Base {
public:
    virtual void print() {
        std::cout << "Base class" << std::endl;
    }
};

class Derived : public Base {
public:
    void print() override {
        std::cout << "Derived class" << std::endl;
    }
};
  ```
#### Override:

The override keyword is used to indicate that a method in a derived class is intended to override a virtual method in the base class. This helps catch errors at compile-time, such as incorrectly providing a new implementation of the method with a different signature.

 ```cpp 
class Base {
public:
    virtual void print() {
        std::cout << "Base class" << std::endl;
    }
};

class Derived : public Base {
public:
    void print() override { // Correctly overrides the base class method
        std::cout << "Derived class" << std::endl;
    }
};
  ```
### Smart Pointers vs. Native Pointers
Memory management is an important aspect of C++ programming. Native pointers, while powerful, can lead to memory leaks and other issues if not managed carefully. Smart pointers, introduced in modern C++, provide a safer and more convenient alternative for managing dynamically allocated memory.

#### Native Pointers:

Native pointers are basic pointers provided by the C++ language, which store the memory address of an object. They require manual memory management, such as allocating memory with new and deallocating with delete.
 ```cpp 
int* ptr = new int;
*ptr = 42;
delete ptr;
  ```

#### Smart Pointers:

Smart pointers are template classes provided by the C++ Standard Library that automatically manage the memory they point to. The most commonly used smart pointers are std::unique_ptr, std::shared_ptr, and std::weak_ptr.

- std::unique_ptr: Represents a unique ownership of a dynamically allocated object. When the unique_ptr goes out of scope, it automatically deallocates the memory it manages.
 ```cpp 
#include <memory>

std::unique_ptr<int> ptr = std::make_unique<int>(42);
```
- std::shared_ptr: Represents shared ownership of a dynamically allocated object. The memory is automatically deallocated when the last shared_ptr that manages the object goes out of scope.
 ```cpp 
    
#include <memory>

std::shared_ptr<int> ptr1 = std::make_shared<int>(42);
std::shared_ptr<int> ptr2 = ptr1; // Both ptr1 and ptr2 share ownership
```   
- std::weak_ptr: Represents a non-owning reference to a dynamically allocated object managed by a shared_ptr. It can be used to break reference cycles in complex data structures.
 ```cpp    
#include <memory>

std::shared_ptr<int> ptr1 = std::make_shared<int>(42);
std::weak_ptr<int> weakPtr = ptr1;
```
## la Standard Template Library (STL)
The Standard Template Library (STL) is a library in the C++ Standard Library that provides a collection of generic algorithms, containers, iterators, and other utilities. The STL enables developers to write efficient, modular, and reusable code without having to reimplement common data structures and algorithms from scratch.
### Concepts and Requirements of a Template Argument

Concepts are a way to specify constraints on template arguments, ensuring that they meet certain requirements. They provide better error messages at compile-time and make code more readable and understandable. In C++20, concepts were introduced as a language feature, which can be used to define custom concepts for your own templates.

A simple example of a concept is the std::equality_comparable concept, which requires that the given type supports the == operator:
 ```cpp  
#include <concepts>

template<std::equality_comparable T>
bool areEqual(const T& a, const T& b) {
    return a == b;
}
```
### Containers in the STL:
Containers in the STL are generic data structures that store objects of a specific type. They are widely used in C++ programming and provide a range of functionalities for various use cases. Some common STL containers are:

- std::vector: A dynamic array that automatically manages its size and can grow or shrink as needed. It provides fast random access and is useful when the number of elements is not known in advance.
```cpp 
#include <vector>

std::vector<int> vec = {1, 2, 3, 4};
vec.push_back(5); // Adds an element to the end
``` 
- std::list: A doubly-linked list that provides fast insertion and deletion of elements at any position but has slower random access. It is useful when the order of elements is important, and frequent insertions or deletions are required.
```cpp 
#include <list>

std::list<int> lst = {1, 2, 3, 4};
lst.push_front(0); // Adds an element to the beginning
 ```    
    
- std::map: An associative container that stores key-value pairs in a sorted order based on the keys. It provides fast look-up, insertion, and deletion of elements and is useful when data needs to be stored and accessed using keys.
```cpp 
#include <map>

std::map<std::string, int> m = {{"one", 1}, {"two", 2}, {"three", 3}};
m["four"] = 4; // Adds a new key-value pair
 ``` 
- std::unordered_map: A hash table that stores key-value pairs in no particular order. It provides fast average-case look-up, insertion, and deletion of elements and is useful when data needs to be stored and accessed using keys but the order is not important.
```cpp 
#include <unordered_map>

std::unordered_map<std::string, int> um = {{"one", 1}, {"two", 2}, {"three", 3}};
um["four"] = 4; // Adds a new key-value pair
 ``` 
### Legacy iterators
Legacy iterators are used to traverse and manipulate elements in containers. They provide a consistent interface for various container types, allowing the same algorithms to be used with different containers. Legacy iterators are categorized into five types based on the operations they support:

1. Input iterators: Can be incremented and dereferenced to read the element. They are used for single-pass algorithms.
2. Output iterators: Can be incremented and dereferenced to write to the element. They are also used for single-pass algorithms.
3. Forward iterators: Can perform all operations of input and output iterators, and can be used for multiple passes over the container.
4. Bidirectional iterators: Can perform all operations of forward iterators, and can also be decremented.
5. Random-access iterators: Can perform all operations of bidirectional iterators, and can also perform arithmetic operations to access elements directly.


### std::shared_ptr
std::shared_ptr is a smart pointer provided by the C++ Standard Library that manages shared ownership of a dynamically allocated object. The memory is automatically deallocated when the last shared_ptr that manages the object goes out of scope.
```cpp 
#include <memory>

std::shared_ptr<int> ptr1 = std::make_shared<int>(42);
std::shared_ptr<int> ptr2 = ptr1; // Both ptr1 and ptr2 share ownership
 ``` 
 
### Iterator Traits
Iterator_traits is a template class in the C++ Standard Library that provides a way to obtain information about iterators at compile-time. It defines nested types that describe the properties of the iterator, such as the value type, difference type, pointer type, reference type, and iterator category.
```cpp 
#include <iterator>
#include <vector>

template<typename Iterator>
typename std::iterator_traits<Iterator>::value_type
sum(Iterator begin, Iterator end) {
    typename std::iterator_traits<Iterator>::value_type result = 0;

    for (Iterator it = begin; it != end; ++it) {
        result += *it;
    }

    return result;
}

int main() {
    std::vector<int> vec = {1, 2, 3, 4, 5};
    int total = sum(vec.begin(), vec.end());
    std::cout << "Sum: " << total << std::endl;
}
 ``` 
## estensioni di C++
As C++ evolves, new features are introduced in the language to improve efficiency, safety, and maintainability. With the release of C++20, a major new feature called "modules" has been introduced to address some of the limitations of the traditional preprocessor-based inclusion model.
### Modules (C++20)
Modules are a modern, efficient, and scalable way to manage dependencies in C++ projects. They offer better compile-time performance, improved code hygiene, and better separation of concerns compared to the traditional header file inclusion model.
In traditional C++ projects, header files are used to declare functions, classes, and other entities that can be shared across multiple source files. However, this model relies on the preprocessor to include the contents of header files, which can lead to slow compilation times, name clashes, and other issues. Modules address these problems by introducing a new way to define and consume interfaces in C++ projects. With modules, entities can be exported from one module and imported into another, enabling better control over what is visible to other parts of the code.
#### Creating and Using Modules
To create a module, use the export module declaration followed by a module name. The export keyword is used to specify which entities should be made visible to other modules that import this module.
```cpp
// my_module.cpp
export module my_module;

export int add(int a, int b) {
    return a + b;
}
 ``` 
To use a module, use the import declaration followed by the module name. This makes the exported entities from the module available in the current translation unit.
```cpp
// main.cpp
import my_module;

int main() {
    int result = add(3, 4);
    std::cout << "Result: " << result << std::endl;
}
 ``` 
To compile a project with modules, use a C++20-compliant compiler and ensure that the module interface files are correctly generated and consumed during the build process. Different compilers may have their own specific flags or options for handling modules.

### Auto and Type Inference (C++11):
Type inference is a feature introduced in C++11 that allows the compiler to automatically deduce the type of a variable based on its initializer. This can make the code shorter, more readable, and less prone to errors. The auto keyword is used to enable type inference in C++.
#### Basic Usage of auto:

The auto keyword can be used to declare a variable whose type is deduced from its initializer. This is particularly useful when the type is complex or when it is subject to change during the development process.
```cpp
#include <iostream>
#include <vector>

int main() {
    auto i = 42; // i is deduced as an int
    auto pi = 3.14159; // pi is deduced as a double

    std::vector<int> vec = {1, 2, 3, 4, 5};
    auto it = vec.begin(); // it is deduced as a std::vector<int>::iterator
}
 ``` 
#### Type Inference in Range-Based For Loops:

The auto keyword is often used in range-based for loops to simplify the syntax and make the code more maintainable. This is especially useful when iterating over containers with complex element types.
```cpp
    std::vector<std::pair<int, std::string>> items = {
    {1, "one"},
    {2, "two"},
    {3, "three"}
};

for (const auto& item : items) {
    std::cout << item.first << ": " << item.second << std::endl;
}
  ```    
#### Type Inference with Functions and Lambdas:

The auto keyword can also be used with function return types and lambdas to enable automatic type deduction based on the function's implementation.

```cpp
// Deduce return type from the function body
template<typename T>
auto multiply(T a, T b) {
    return a * b;
}

// Deduce lambda argument and return types
auto add = [](auto a, auto b) {
    return a + b;
};
  ```    
    
### Lambda Expressions (C++11)
    
Lambda expressions, introduced in C++11, are a concise way to define anonymous function objects (functors) at the point where they are used. They are particularly useful for small, single-use functions that would be cumbersome to define separately.
#### Basic Syntax of Lambda Expressions:

A lambda expression has the following form:
```cpp
    [capture_list](parameters) -> return_type {
    // function body
};
 ```   
> - capture: The capture clause specifies which variables from the surrounding scope should be available inside the lambda body. Variables can be captured by value (copied) or by reference.
> - parameters: The parameter list for the lambda, just like for regular functions.
> - return_type: The return type of the lambda. This is optional and can often be deduced automatically by the compiler.
> - body: The body of the lambda, which contains the code to be executed.
```cpp
 auto sum = [](int a, int b) {
    return a + b;
};

int result = sum(3, 4); // result is 7
```    
#### Capture Modes:

There are several ways to capture variables from the surrounding scope in a lambda expression:

- Capture by value: [x] captures the variable x by value, so the lambda has its own copy of x.
- Capture by reference: [&x] captures the variable x by reference, so the lambda refers directly to the original variable.
- Capture all by value: [=] captures all variables in the surrounding scope by value.
- Capture all by reference: [&] captures all variables in the surrounding scope by reference.
- Capture by value and reference: [x, &y] captures x by value and y by reference.
    
 ```cpp   
    int x = 1;
int y = 2;

auto sum = [x, &y]() {
    // Access x by value (copy) and y by reference
    return x + y;
};
 ```    
#### Lambda Expressions and Standard Algorithms:

Lambda expressions are often used with standard algorithms from the C++ Standard Library, such as std::sort, std::transform, or std::for_each.
 ```cpp   
    #include <algorithm>
#include <vector>
#include <iostream>

int main() {
    std::vector<int> numbers = {6, 3, 8, 1, 5};

    // Sort in descending order using a lambda
    std::sort(numbers.begin(), numbers.end(), [](int a, int b) {
        return a > b;
    });

    // Output: 8 6 5 3 1
    for (int n : numbers) {
        std::cout << n << ' ';
    }
}
 ``` 
### Decltype Construct (C++11 and C++14):
The decltype construct, introduced in C++11 and refined in C++14, is used to deduce the type of an expression. It can be useful in situations where the type of an expression is not known at compile-time or when writing generic code.

#### Basic Usage of decltype:

decltype can be used to declare a variable whose type is the same as the type of a given expression.
 ```cpp   
    int x = 42;
decltype(x) y = x; // y has the same type as x (int)
 ```     
    
####  decltype in Generic Code:

decltype is particularly useful in writing generic code where the type of an expression depends on template parameters. In the example below, decltype is used to deduce the return type of a generic function.
 ```cpp   
    template<typename T, typename U>
auto add(T a, U b) -> decltype(a + b) {
    return a + b;
}

int main() {
    int a = 3;
    double b = 4.5;
    auto result = add(a, b); // result has type double
}
 ``` 
#### decltype and auto:

decltype can be used in conjunction with auto to deduce the type of a variable based on the type of another variable, while also applying certain transformations. For example, when declaring a reference to a variable, decltype can be used to deduce the correct reference type.
   ```cpp 
    int x = 42;
auto& y = x; // y is an int&
decltype(x)& z = x; // z is also an int&
  ```    
    
#### decltype(auto) (C++14):

In C++14, the decltype(auto) specifier was introduced to deduce the type of a variable or the return type of a function based on the type of its initializer or the type of the expression returned, respectively. This is particularly useful when working with expressions that return references or when using perfect forwarding.
```cpp
template<typename T>
decltype(auto) identity(T&& x) {
    return std::forward<T>(x);
}

int main() {
    int x = 42;
    decltype(auto) y = identity(x); // y is an int& and refers to x
}
 ``` 
