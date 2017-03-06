#-----------------------------------------------------------------------------------------------
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
#-----------------------------------------------------------------------------------------------
QUIT_STRINGS = ["quit","q","exit","e"]
YES_STRINGS = ["yes","y"]
LINES = ["-","+","=","*","^"]

class Consoul:
    instances = 0

    def __init__(self):
        self.current_menu = ""
        self.menus_by_id = dict()
        self.show_debug = False
        self.linelength = 20
        self.linestyle = LINES[0]
        self.shortlinelength = 10
        self.shortlinestyle = LINES[1]
        self.close = False
        Consoul.instances += 1
        self.id = Consoul.instances-1
    
    def exit(self):
        self.close = True
    
    def __enter__(self):
        return self
    
    def __exit__(self,exc_type,exc_value,traceback):
        return self

    def on_exit(self):
        if self.show_debug:
            self.output("Goodbye")
            self.output("Consoul instance "+str(self.id)+" closed")
            self.output("Consoul_Menu instances: "+str(Consoul_Menu.instances))
            self.output("Consoul_Action instances: "+str(Consoul_Action.instances))    
        else:
            pass
    
    def add_menu(self,id,parent):
        self.menus_by_id[id] = Consoul_Menu(self,id,parent)
        return self.menus_by_id[id]

    def output(self, text, color="white"): 
        print(text)        
        
    def shortline(self):
        line = ""
        for x in range(self.shortlinelength):
            line+=self.shortlinestyle
        self.output(line)
        
    def line(self):
        line = ""
        for x in range(self.linelength):
            line+=self.linestyle
        self.output(line)
    def list_menu_actions(self,menu):
        for x in range(len(menu.consoul.actions)):
            self.output("["+str(x)+"] "+menu.consoul.actions[x].name)
        if menu.show_quit: self.output("[q] Quit")
    
    def output_choices(self,lists):
        for x in range(len(lists)):
            self.output("["+str(x)+"] "+lists[x])
    
    def handle_entry(self,entry,menu):
        if entry.isdigit():
            return int(entry)
        elif entry.lower() in QUIT_STRINGS:
            menu.quit = True
        else:
            return -1
            
    def enter(self,entry):
        self.close = False
        if entry not in self.menus_by_id.keys(): 
            self.output("Invalid Menu id: "+str(entry))            
            menu.quit = True
            self.on_exit()
            return
        menu = ""
        if entry == "parent":
            menu = self.current_menu.parent 
        else:
            menu = self.menus_by_id[entry]
        self.current_menu = menu                
        while not menu.quit and not self.close:
            self.line()
            self.output(menu.name)
            self.line()
            self.list_menu_actions(menu)
            self.shortline()
            while True:
                entry = self.handle_entry(input("Enter an action by number: "),menu)
                self.shortline()
                if menu.quit: break                
                if entry>=0 and entry<len(menu.consoul.actions):
                    break #Valid entry
                else:
                    self.output("Please enter a valid action number")
                    self.shortline()
                    continue
            if menu.quit: break
            menu.action_calls.append(menu.consoul.actions[entry].id)
            menu.consoul.actions[entry].action()
            self.shortline()
        menu.quit = False
        if menu.level == 0 or self.close:
            self.on_exit()
            return

#-----------------------------------------------------------------------------------------------
# Consoul_Menu
# Menu class for consoul.
#
# Written by: Eric Dudley

# Features
#   -Initialization of menu attributes and methods
#   -List of consoul.actions that the user can choose, including submenus
#   -Colored output
#   -Navigation memory
#   -Menu data storage, permanent, session, and temporary

#For condensed version for final releases go to -> 
#For examples of the types of applications that can be created using Consoul go to -> 
#For guides on how to use the features of Consoul go to -> 
#-----------------------------------------------------------------------------------------------
class Consoul_Menu:
    instances = 0

    def __init__(self,consoul,id,parent):
            self.consoul = consoul                
            self.id = id
            self.name = "Default Menu"
            self.show_quit = True
            self.quit = False
            self.consoul.actions = []
            self.actions_by_id = dict()
            self.action_calls = []
            if not parent:
                self.parent = False
            else:
                self.parent =  consoul.menus_by_id[parent]
            self.level = self.getLevel(self) 
            Consoul_Menu.instances += 1

    def add_action(self,action):
        self.consoul.actions.append(action)
        self.actions_by_id[action.id] = action
        action.consoul = self.consoul
        action.menu = self

    def getLevel(self,menu):
        if menu.parent == False:
            return 0
        else:
            return self.getLevel(menu.parent)+1

#-----------------------------------------------------------------------------------------------
# Consoul_Action
# consoul.actions.Action class to be listed in menus.
#
# Written by: Eric Dudley

# Features
#   -consoul.actions.Action instances so that classes can be used in multiple menus without sharing data memory
#   -Actions can also be designed to share menu memory  
#   -Extremely flexible consoul.actions
#   -Open consoul instance or enter consoul menu from within an action
#   -Colored output
#   -Navigation memory
#   -Menu data storage, permanent, session, and temporary

#For condensed version for final releases go to ->
#For examples of the types of applications that can be created using Consoul go to ->
#For guides on how to use the features of Consoul go to ->
#-----------------------------------------------------------------------------------------------
class Consoul_Action:
    instances = 0

    def __init__(self,id,cls,name="Default consoul.actions.Action"):
                self.consoul = True
                self.menu = True
                self.id = id
                self.name = name
                self.data = []
                cls.instances += 1
                Consoul_Action.instances += 1
    
    def action(self):
        self.consoul.output("action() function not yet implemented for action id: "+str(self.id))
            
#-----------------------------------------------------------------------------------------------
#Default behavior and consoul.actions for when consoul module is run itself.
#-----------------------------------------------------------------------------------------------
import ftplib,os

class DisplayInfo(Consoul_Action):
    instances = 0
    def __init__(self,id):
        Consoul_Action.__init__(self,id,DisplayInfo,name="Consoul Info")
        self.info = dict()
        self.info["Version"] = "1.0.0"
        self.info["Author"] = "Eric R Dudley"
        self.info["Update Date"] = "10-7-15"
        self.info["Website"] = "ericdudley.com"
        self.info["Description"] = "A python module that allows console based programs with complex functions to be developed.\nUseful for developing especially large scale programs."
        
    def action(self):
        for item in self.info.items():
            self.consoul.output(str(item[0])+": "+str(item[1]))
        
class CheckVersion(Consoul_Action):
    instances = 0
    def __init__(self,id):
        Consoul_Action.__init__(self,id,CheckVersion,name="Check Version")
        
    def action(self):
        self.consoul.output("Your version may or may not be good.")
        
if __name__ == "__main__":
    consoul = Consoul()
    main = consoul.add_menu("main",False)
    main.name = "Consoul Info"
    main.add_action(DisplayInfo("display_info"))
    main.add_action(CheckVersion("check_version"))
    consoul.enter("main")