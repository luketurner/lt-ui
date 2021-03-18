(ns ulti.docs.containers
  (:require [ulti.docs.devcards :refer-macros [defcard']]))

(defcard' application-container-overview
  "The `.application` class provides a top-level container with conventional Web application layouts.
   
   The most basic usage is to create a `<main>` child element that contains the main content for the application:

   ``` clojure
   [:div.application
     [:main \"This is my content!\"]]
   ```

   The `.application` div takes up the entire visible screen. However, the `main` sub-element is width-limited for readability.
   
   If you add a `header` component child, it will be displayed as a navbar (i.e. on top of the main content.)

   ``` clojure
   [:div.application
     [:header \"Navbar\"]
     [:main \"This is my content!\"]]
   ```
   
   You can also add a footer:
   
   ``` clojure
   [:div.application
     [:header \"Navbar\"]
     [:main \"This is my content!\"]
     [:footer \"Footer\"]]
   ```
   
   You can also add sidebars using the `.sidebar-left` and `.sidebar-right` classes.
   
   ``` clojure
   [:div.application
     [:header \"Navbar\"]
     [:section.sidebar-left \"Left sidebar\"]
     [:main \"This is my content!\"]
     [:section.sidebar-right \"Right sidebar\"]
     [:footer \"Footer\"]]
   ```

   The above example positions the `main` element between the left and right sidebar elements for clarity,
   but there is no required order. For example, this renders the same thing:

   ``` clojure
   [:div.application
     [:section.sidebar-right \"Right sidebar\"]
     [:section.sidebar-left \"Left sidebar\"]
     [:footer \"Footer\"]]
     [:main \"This is my content!\"]
     [:header \"Navbar\"]
   ```

   All the elements are separated by a consistent gutter (which is calculated from the line-height of your theme.)
   
   The example below illustrates all of these options: a layout with a header, footer, and both sidebars."
  [:div.application.utility-application-demo.utility-fill-color
   [:header.utility-fill-color "This is a navbar"]
   [:section.sidebar-left.utility-fill-color "Left sidebar 1"]
   [:main.utility-fill-color "This is my content!"]
   [:section.sidebar-right.utility-fill-color "Right sidebar"]
   [:footer.utility-fill-color "This is a footer"]])