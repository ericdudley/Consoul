# Consoul
# Console application menu management library.
#
# Written by: Eric Dudley

# Features
#   -Consoul initialization
#   -Menu initialization and navigation
#   -Submenus
#   -Colored output
#   -Navigation memory
#   -Menu data storage, permanent, session, and temporary
#   -Menu functions

#For condensed version for final releases go to ->
#For examples of the types of applications that can be created using Consoul go to ->
#For guides on how to use the features of Consoul go to ->

import Consoul
from Consoul import Consoul_Action
import datetime

class NamePrompt(Consoul_Action):   
    instances = 0

    def __init__(self,id):
        Consoul_Action.__init__(self,id,NamePrompt)
        self.name = "Name Prompt"        
        self.names = []
        self.form = ""
    
    def action(self):
        del self.names[:]
        while len(self.names)<2:
            full_name = input("Enter your full name: ")
            for x in full_name.split(" "):
                self.names.append(x)
            if len(self.names) < 2:
                del self.names[:]
                continue
            elif len(self.names) == 2:
                self.form = "First Last"
            elif len(self.names) == 3 and len(self.names[1]) == 1:
                self.form = "First MiddleInitial Last"
            else:
                self.form = "First Middle Last"
            self.data.append((list(self.names),self.form))
            self.consoul.output("Name: "+full_name+"\nIn the form: "+self.form)
        
class BirthdayPrompt(Consoul_Action):
    instances = 0

    def __init__(self,id):
        Consoul_Action.__init__(self,id,BirthdayPrompt)
        self.name = "Birthday Prompt"
        self.birthday = dict()

    def action(self):
        correct = False
        months = []
        for i in range(1,13):
            months.append((datetime.date(2008, i, 1).strftime('%B')))
        while True:        
            self.birthday["month"] = input("Enter month of your birthday as a number 1-12: ")
            self.birthday["day"] = input("Enter day of your birthday as a number: ")
            self.birthday["year"] = input("Enter year of your birthday as a number: ")
            self.birthday["string"] = months[int(self.birthday["month"])-1]+" "+str(self.birthday["day"])+", "+self.birthday["year"]
            self.data.append((self.birthday["month"],self.birthday["day"],self.birthday["year"]))

            self.consoul.output("Birthday is "+self.birthday["string"])
            if input("Is this correct? ").lower() in Consoul.YES_STRINGS:
                break
            else:
                self.consoul.output("Let's try again.")
                continue
        
        
