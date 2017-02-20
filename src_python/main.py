# Example Program
# Using Consoul
# Written by: Eric Dudley

from main_menu_actions import *
from Consoul import Consoul


consoul = Consoul()

main = consoul.add_menu("main",False)
main.name = "Main Menu"
main.add_action(NamePrompt("name_prompt"))
main.add_action(NamePrompt("name_prompt2")) #Multiple instances of same action must have DIFFERENT ids
main.add_action(BirthdayPrompt("birthday_prompt"))
main.add_action(BirthdayPrompt("birthday_prompt2"))

consoul.enter("main")
