# Pre-work - *GotToDo*

**SimpleToDo** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Rahul Deshpande**

Time spent: **8** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)	
* [X] Add support for completion due dates for todo items (and display within listview item)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [X] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds


The following **additional** features are implemented:

* [X] Today's date at the top of the screen :- this helps the user to get a perspective of task list matched against the current date
* [X] Count of pending item :- this helps the user get pending task summary (work load) at a quick glance.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='SimpleToDo_Demo4.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** Android development platform has come a long way since it was launched. The tools and development guidelines seem mature and easy to understand and use. The fragmentation of the platform however  presents some interesting challenges and design decisions during product development. I come from a web background that typically involved MVC patterned layouts and interactions. Android's approach to layouts (relative, linear, fixed) feel familiar and allow an easy transition. Also, concepts of fragments does allow reusability which has been typically hard to do in web frameworks (much better now with ReactJS based approaches).

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** An adapter essentially allows separation of an object's implementation and it's rendering. It allows developers to decouple data and views in turn providing greater flexibility and speed of development. The "convertView" parameter represents an already instantiated view that is available for use (recycling) again. This allows faster rendering of lists by avoiding instantiating new views for each item that is rendered. Since only the list items that are viewable on a screen need to be kept in memory, already instantiated views could be recyled every time user scrolls up or down.

## Notes

Describe any challenges encountered while building the app.
1. Insufficient documentation on DBFlow best practices created some challenges in integration. It took some effort and search to identify better ways to perform certain operations like record updates.
2. Keeping the app simple: ToDo list applications tend to be overcrowded with features that eventually hurt productivity. This is true for other apps as well that implement simple yet powerful tools and concepts. Adding too many features dilute the original purpose of the application. I am trying to incorporate minimalism in my app while keeping the usefulness.

## License

    Copyright [2017] [Rahul Deshpande]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.