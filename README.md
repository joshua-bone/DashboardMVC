# DashboardMVC
# By Joshua Bone

Dashboard is an ongoing project to create a comprehensive productivity center for your everyday life, combining your calendar, grocery lists, and to-dos with an integrated tracking and grading system which lets you know at a glance how well you are doing at meeting your defined goals.

So how does it work? Well, it doesn't... yet. But the functionality that does work starts with one of the app's base classes: Index Cards. These are essentially file folders you can use to organize your life into categories. Unlike a typical directory structure on your computer, however, any Index Card may be nested underneath multiple parents.

Another base class is called a Dashboard Item, for lack of a better term. Dashboard Items may fall into several subclasses. For example, consider the following list of goals you might write for yourself:
    -I need to go Christmas shopping next Thursday
    -I should remember to take the trash to the curb every Tuesday
    -I want to run at least 10 miles per week
    -I want to quit smoking
    -I want to get a haircut every 3-5 weeks
All of these are examples of a Dashboard Item. The purpose of the app is to allow the user to create any Item they can dream up and attach it to an Index Card. The app will then have a set of rules to automatically schedule the Item somewhere on the user's calendar, if applicable, as well as generate a letter grade (A, B, C, D, or F) on a daily, weekly, and monthly basis to show the user how well they are meeting their own goals. 
