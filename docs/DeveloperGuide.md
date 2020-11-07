<h1 align="center">  CheatLogs User Guide </h1>


![Our Logo](https://i.ibb.co/VxzKbbz/a1.png)

Welcome to the official developer guide of CheatLogs, a free and open-source cheatsheet manager with blazing fast organization and searching via a command-line interface (CLI). 	

This guide serves as an in-depth reference of the features of CheatLogs.


> <font size = "5" >:bulb: Here are some patterns you will come across and their definitions.
> ------
>|Pattern|Definition|
>|--|--|
>| :bulb: |Tip on current section  |
>|:exclamation:|Warning of potential error|
>|<font size="3"> [:arrow_up_small:](#table-of-contents)</font>| Returns to table of contents on left click|
>|**bold**|Key terms specific to CheatLogs|
>|*italics*|Files|
>|`Snippets`|Typed input or output going into or out of CheatLogs|

<br>

# Table of Contents

* [1. Introduction](#introduction)
    + [1.1. Purpose](#purpose)
    + [1.2. Product Scope](#product-scope)
        - [1.2.1. Target User Profile](#target-user-profile)
        - [1.2.2. Value Proposition](#value-proposition)
    + [1.3. Design Goals](#design-goals)
    + [1.4. Definitions](#definitions)
* [2. Setting Up, Getting Started](#setting-up-getting-started)
    + [2.1. Prerequisites](#prerequisites)
    + [2.2. Running the Project](#running-the-project)
    + [2.3 Importing into IntelliJ [optional]](#importing-into-intellij)
* [3. Design](#design)
    + [3.1. Architecture](#architecture)
    + [3.2. Components](#components)
        - [3.2.1. User Interface](#user-interface)
        - [3.2.2. Command Parser](#command-parser)
        - [3.2.3. Cheat Sheet Structure](#cheat-sheet-structure)
            * [3.2.3.1. Add](#add)
            * [3.2.3.2. Edit](#edit)
            * [3.2.3.3. View](view)
            * [3.2.3.4. Exit](#exit)
            * [3.2.3.5. List](#list)
            * [3.2.3.6. Find](#find)
            * [3.2.3.7. Setting](#setting)
            * [3.2.3.8. Delete](#delete)
            * [3.2.3.9. Clear](#clear)
            * [3.2.3.10. Favourite](#favourite)
            * [3.2.3.11. Exit](#help)
        - [3.2.4. Cheat Sheet Management](#cheat-sheet-management)
        - [3.2.5. Data Storage](#data-storage)
* [4. Implementation](#implementation)
    + [4.1. Parsing of Data to Construct Commands](#parsing-of-data-to-construct-commands)
    + [4.2. Editing Feature](#editing-feature)
    + [4.3. Sorting Feature](#sorting-feature)
    + [4.4. Storage & Reading of Cheatsheet files](#storage-reading-of-cheatsheet-files)
    + [4.5. Colour coding for code snippet](#colour-coding-for-code-snippet)
* [5. Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
* [6. Appendix: Requirements](#appendix-requirements)
    + [6.1. Product scope](#product-scope-appendix)
    + [6.2. User stories](#user-stories)
    + [6.3. Use cases](#use-cases)
    + [6.4. Non-Functional Requirements](#non-functional-requirements)
    + [6.5. Glossary](#glossary)
* [7. Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)

<br>

<a id="introduction"></a>
# 1. Introduction<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

**CheatLogs** is a Command Line Interface Application that is targeted at novice or beginner programmers to help them develop their own applications. 
It has a text based interface that allows the user to access cheat sheets and notes for various programming syntax and functions. 

The user can quickly `find` and `view` the preloaded cheat sheets and it will be displayed with nice color coding for better readability.
Moreover, the user can `add`, `edit`, and `delete` their own cheat sheets.


<a id="purpose"></a>
## 1.1. Purpose<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This document specifies the architecture and software design decisions taken to develop our application, CheatLogs. 
Targeted towards developers who are or want to work on CheatLogs.


<a id="product-scope"></a>
## 1.2. Product Scope<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This document will cover the high-level architecture of the program, as well as the details for the implementation of the features.

---

<a id="target-user-profile"></a>
### 1.2.1. Target User Profile<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
- has a need to store and manage a significant number of cheat sheets
- is willing to learn/is comfortable using CLI application

---

<a id="value-proposition"></a>
### 1.2.2. Value Proposition<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

The motivation behind CheatLogs is to reduce the time spent to scroll through pages of stack overflow posts or online documentation sites to find a syntax for a programming language,
which is very common for novice programmers. The user can find the desired syntax by using `/find` command and the result will show up instantly.

---


<a id="design-goals"></a>
## 1.3. Design Goals<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

Our main goal is to design a cheatsheet management system that will help novice and beginner programmers to learn and adapt to various programming languages. 
Our cheatsheet app comes with a set of preloaded cheatsheets. 
Additionally, the user can input their own cheat sheets into the application. 
This will make cheatlogs a comprehensive, one-stop solution for every student to manage their cheatsheet.


<a id="definitions"></a>
## 1.4. Definitions<font size="5"> [:arrow_up_small:](#table-of-contents)</font>


<br>

<a id="setting-up-getting-started"></a>
# 2. Setting Up, Getting Started<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
xx


<a id="prerequisites"></a>
## 2.1. Prerequisites<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

1. JDK 11.0.8
2. Java IDE (IntelliJ Recommended)

CheatLogs has been developed on Java 11 and may not be supported on other versions. 
You are free to use any java file editor and run the program by following the step by step guide below. 


<a id="running-the-project"></a>
## 2.2. Running the Project<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest CheatLogs.jar from [here](https://github.com/AY2021S1-CS2113T-W11-3/tp/releases).
3. Move the file to a folder you want to use as the home folder for this application.
4. Invoke java -jar CheatLogs.jar on the command line to run the program. A welcome message should appear, as shown below.

![](Images/Image1.PNG)


<a id="importing-to-intellij"></a>
## 2.3 Importing into IntelliJ [optional]<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

In case you prefer to use IDEs to develop. 
IntelliJ IDEA community edition is a popular free choice. 
After installing a version of it you can import it using the “Get from Version Control” option below and clone from our [repository](https://github.com/AY2021S1-CS2113T-W11-3/tp.).


![](Images/Image2.PNG)

<br>

<a id="design"></a>
# 3. Design<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This section will elaborate on the architecture and component design of CheatLogs.


<a id="architecture"></a>
## 3.1. Architecture<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

The image below illustrates the high-level design of CheatLogs.

![](Images/Image3.PNG)

Image 1: General Architecture of CheatLogs

CheatLogs is split into 5 major components, each handling distinct features of the application. The components and a brief description of them is listed below.
* `UI`: The user interface of the app.
* `CheatSheet`: The structure of each cheat sheet
* `CheatSheetList`: A collection of every cheat sheet.
* `Parser`: Builds a data structure based on user inputs.
* `Command`: An encapsulation of data and methods to execute each command
* `Storage`: Updates application data based on relevant external files.

The UML diagram below illustrates an extensive version of the various classes present in CheatLogs as well as their interactions with each other.


![](Images/Image4.PNG)

Image 2: In-Depth Architecture of CheatLogs

For each component, it can be further split into different classes which have a unique responsibility. They will be further elaborated upon in the next section.


<a id="components"></a>
## 3.2. Components<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This application contains five different components. 
Each component has a unique function and contributes to the functionality of this application.

---

<a id="user-interface"></a>
### 3.2.1. User Interface<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//Abner
This component handles interactions with the user and manages the input and output of the programme. 

![](Images/Image5.PNG)

Image 3: User Session class fields and methods

One of the classes is UserSession which contains the main loop of the program. 
Every loop it reads and parses the user input to get a Command object which encapsulates all the necessary information needed to execute the command. 
After execution, it handles potential exceptions thrown.


A single instance of common objects are usually created in UserSession, such as Ui and Printer objects. 
These common objects are injected into other objects that need them via the class constructor instead of static methods.
The common objects include the Ui and  Printer helper classes which provide an organized way to read and write data. 
Most of the programmes’ output is made via calls to the same common printer object.

#### 3.2.3. Command (Brandon)

<a id="command-parser"></a>
### 3.2.2. Command Parser<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//Brandon
This component would parse the user input to produce useful information which would be used to construct a Command and executed the command. 

![](Images/Image6.PNG)

Image 4: Interaction between components to parse and execute command

![](Images/Image7.PNG)

Image 5: Sequence Diagram from parsing input to command execution

Image 4 and 5 illustrates the interactions between Parser, CommandExecutor and Command when a user inputs an instruction. 

The steps below explain the sequence diagram:
1. User input an instruction
2. A new Parser object is created
3. Parser#parse() is called to extract command type, name, programming language and details from the user-inputted instruction
4. If the user input is valid, a new CommandExecutor object would be created
5. CommandExecutor#execute() would be called to create a new Command object according to type of command user inputted
6. Command#execute() would call other methods from CheatSheet and CheatSheetList to carry out specific instructions.


<a id="cheat-sheet-structure"></a>
### 3.2.3. Cheat Sheet Structure<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//Aldo

<a id="add"></a>
##### 3.2.3.1 Add


<a id="edit"></a>
##### 3.2.3.2 Edit

<a id="view"></a>
##### 3.2.3.3 View

<a id="exit"></a>
##### 3.2.3.4 Exit

<a id="list"></a>
##### 3.2.3.5 List

<a id="find"></a>
##### 3.2.3.6 Find

<a id="setting"></a>
##### 3.2.3.7 Setting 

<a id="delete"></a>
##### 3.2.3.8 Delete

<a id="clear"></a>
##### 3.2.3.9 Clear

<a id="favourite"></a>
##### 3.2.3.10 Favourite

<a id="help"></a>
##### 3.2.3.11 Help

<a id="cheat-sheet-structure"></a>
#### 3.2.4. Cheat Sheet Structure 
//Aldo

To further understand our cheatsheet management application, it is important to understand the inner workings of the CheatSheet class. 
CheatLogs comes with preloaded cheat sheets and the application has an in-built ability for the user to add, edit, and delete their own cheat sheets. 
The preloaded cheat sheet cannot be modified by the user, as the user-made cheatsheet is able to be modified by the user.


The CheatSheet class is the class that models the cheat sheet that we used in our program. 
Each of our cheatsheet object is intended to store one cheatsheet topic for one specific programming language (both preloaded and user-created cheat sheets).

This system is intended to increase user access and organization of the cheat sheets.
Each of the CheatSheet object has:
1. 	Name
2. 	Programming Language
3. 	Description (Content of the cheatsheet)
4. 	isFavorite
5. 	isModifiable
6. 	Date Created
7. 	Date Modified

![](Images/Image8.PNG)

Image 6: class diagram of the cheatsheet class

---

<a id="development-goals"></a>
###  Development Goals<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//TBC
We are trying to move our existing cheatsheet file format from txt to xml. 
XML files provide inbuilt tags to differentiate and classify members of the document. 
With the use of XML file, it simplifies the parsing process of the cheatsheet files into the cheatsheet class, and vice versa.

![](Images/Image9.PNG)

Image 7: XML file showing the content of a cheatsheet file



<a id="cheat-sheet-management"></a>
### 3.2.5. Cheat Sheet Management<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//Adhy
All cheat sheets, both pre-loaded and user-defined, are stored in a class called `CheatSheetList` during runtime. 
Upon receiving a valid input from the user, the `execute()` method from `Command` class will invoke the mutation of `CheatSheetList`, 
and then `DataFileWriter` will use the information in `CheatSheetList` to save it to the memory in the form of a `xml` file.

![](Images/Image10.PNG)

Image 8: Class diagram of CheatSheetList class

The cheatSheets ArrayList is of type private, and setter/getter methods must be invoked to access the list. 
For these setter and getter methods, it accepts both accessing by its index and accessing by its name to allow the user to easily search and fetch the cheat sheet.
Here is the list of `Commands` that invokes a method call of CheatSheetList:
- `/add`
- `/clear`
- `/delete`
- `/fav`
- `/find`
- `/list`
- `/view`

<a id="data-storage"></a>
### 3.2.6. Data Storage<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//Theo
This feature allows the application to read and update data in the form of text files. 
Having an external source to store data will allow the application to be able to retrieve it when it is relaunched at another instance. 
This prevents the user from having to repeatedly create new cheatsheets and update the application settings each time he opens up CheatLogs. 


This feature contains 3 functions, which are indicated in Table 1 below.



| Function | Purpose of Function |
|----------|--------------------------------|
|Read Files|Allows CheatLogs to extract cheatsheets and user-defined settings from text files provided that they follow a specified format.|
|Write Files|Allows CheatLogs to write cheatsheets and user-defined settings to the hard-disk in the form of text files.|
|Delete Files|Allows CheatLogs to remove unnecessary files from the hard-disk.|
Table 1: Summary of the functions of data storage

---

<br>

<a id="implementation"></a>
# 4. Implementation<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This section describes some noteworthy details on how certain features are implemented.


<a id="parsing-of-data-to-construct-commands"></a>
## 4.1. Parsing of Data to Construct Commands<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

The command classes follow the command pattern. 
During construction in Parser.parse() they get the information they need to execute from parsed data based on the user input. 
Flags in the input are used to separate the different information. (e.g. `/add /n if /l python /k is nice`, has 3 flags, /n /l and /k and the String “python" is associated with the flag /l). 
This information is stored in a HashMap where the descriptor (defined in ArgumentEnum) of the flag is the key and the information associated with it is the value.
The command can execute at a random time later via commandObj.execute().12


<a id="editing-feature"></a>
## 4.2. Editing Feauture<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

The editing feature is enabled using a simple text editor that uses a graphical user interface. 
The strong reason that we wanted to use a GUI is that it offers more flexibility and editing power for the user rather than just using command-line editing methods.


The editing feature is handled by the TextEditor class. The Text Editor inherits from JFrame and implements the Action Listener class. 
The text editor is instantiated when the edit command is invoked. 


<a id="sorting-feature"></a>
## 4.3. Sorting Feature<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This feature allows cheat sheets to be sorted in ascending or descending order according to the name or programming language of the cheat sheet.


This feature is facilitated by ListCommand class. 
It made use of Collections.sort along with sortByName and SortByLanguage which both implements Comparator<CheatSheet>. 


Collections and Comparator forms the private method askForSortingConfigAndPrint(). 
The inclusion of the method allows the user to choose the sorting option and the sorted cheat sheets will be printed.	


Alternative: Using a for loop to sort by name and another loop that sorts by language
Con: There would be many duplicate code and not good for reusability. 
By using the sort() method present in java. util. Collections class, we would have better flexibility as the sort method could be reused with different functions just by including a new class that implements comparable.


<a id="storage-reading-of-cheatsheet-files"></a>
## 4.4. Storage & Reading of Cheatsheet files<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

This feature stores cheat sheets on the hard-drive in the form of a text file. 
When the application is loaded subsequently, data from these files will be converted and loaded into the application.


<a id="colour-coding-for-code-snippet"></a>
## 4.5. Colour coding for code snippet<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
//Proposed
The idea of this feature is to improve the readability of the code snippets (if present) inside the cheat sheet. 
To make this possible, several adjustments must be made to the save data format to allow more information to be stored inside a single file instead of scattered across multiple files. 
Thus, instead of saving to a txt file, the program will write the cheat sheet data into a xml file, for easier parsing and sectioning. 


Given below is an example to illustrate the mechanism.
1. The user launches the application and will be prompted to enter a command
2. The user chooses the /add command, and will be prompted to fill three fields: name, programming language, and details
3. When filling the details, a text editor will pop up. The user now can add their cheat sheet inside this field and he/she can indicate the sections of the cheatsheet using tags similar to markdown
4. The DataFileParser will parse the sections and store the information inside a xml file, with a different tag for each section
5. When the user invokes the /view command, the Printer class will interpret these tags and prepend and append ANSI escape codes to the text


Design consideration:
Alternative 1 (current choice): Saves the file using a xml file, with different tags for each section
* Pros: Allows to store all data in a single file
* Cons: The parsing and saving to the xml file is quite complicated


Alternative 2: Save different sections of the cheat sheet using a different file, and store the files in a folder according to its language
* Pros: The implementation of the parser and file writer is much easier
* Cons: Sections of a single cheatsheet is scattered across multiple files

<br>

<a id="documentation-logging-testing-configuration-dev-ops"></a>
# 5. Documentation, logging, testing, configuration, dev-ops<font size="5"> [:arrow_up_small:](#table-of-contents)</font>


<br>

<a id="appendix-requirements"></a>
# 6. Appendix: Requirements<font size="5"> [:arrow_up_small:](#table-of-contents)</font>


<br>

<a id="product-scope-appendix"></a>
## 6.1 Product scope<font size="5"> [:arrow_up_small:](#table-of-contents)</font>



<a id="user-stories"></a>
## 6.2 User stories<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
The table below describes the particular needs of relevant users that the respective
versions of CheatLogs was designed to solve.

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see a short and comprehensive guide|refer to them when I forget how to use the application|
|v1.0|user|search and filter the cheat sheets by name|open them quickly and easily|
|v1.0|intermediate user|write and add my own cheat sheets|use the application to help me in matters not strictly related to programming|
|v1.0|experienced user|delete all cheat sheets|create and customize everything by myself|
|v2.0|user|edit the cheat sheets|update the cheat sheet and keep them relevant as the time progresses|
|v2.0|user|customize the settings|add and edit the cheat sheets|


<a id="use-cases"></a>
## 6.3 Use cases<font size="5"> [:arrow_up_small:](#table-of-contents)</font>



<a id="non-functional-requirements"></a>
## 6.4 Non-Functional Requirements<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
{Give non-functional requirements}


<a id="glossary"></a>
## 6.5 Glossary<font size="5"> [:arrow_up_small:](#table-of-contents)</font>
* *glossary item* - Definition


<br>

<a id="appendix-instructions-for-manual-testing"></a>
# 7. Appendix: Instructions for manual testing<font size="5"> [:arrow_up_small:](#table-of-contents)</font>

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}



<!---
There are few major components inside the code, and those components are broken down into separate classes. 
All components can be accessed from the CheatLogs class.
### CheatSheetList
All cheatsheets are stored inside a static class CheatSheetList to allow other classes to easily access and write data to the list.
-->
