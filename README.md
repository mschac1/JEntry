# JEntry
Personal project - Java clone of Microsoft Access - combining easily creatable interactive database forms with unlimited extendability through Java - still in development

Running this project requries a database. Currently, the project is set to use a mySQL database with a database named testDB1, a table named, TABLE1, and fields named FIRST_NAME (varchar), LAST_NAME (varchar), AGE (integer), and ACTIVE (bit).
You can use the table1.grab file to create such a table

I found Access to be great and designing simple database apps, but it but the extendability was limited by the VBA language (one simple example, I could not add control arrays)
So I decided, just as personal project, to see whether I could create developement tools similar to those found iiin access, but in Java.
At this point, I have successfully implemented DBForm, DBTextBox, and DBCheckBox (in addition to the underlying framework classes that allow these classes to work)
This allows developers to create a DBFrom and add DBComponents to it which will then function similar to an Access form,  providing record forward, back, etc. operations, as well as modification, save, and delete
The GUI works well, but it is a drop buggy.
Future developement could include
1. Add DBLabel, DBComboBox, DBListBox
2. Add control source string parsing
3. Add Reports
4. Add developement GUIs
