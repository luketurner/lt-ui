(ns lt-ui.containers
  (:require [lt-ui.devcards :refer-macros [defcard']]
            [garden.selectors :as s]
            [garden.units :refer [vh vw rem px]]))

(defn rules [{:keys [line-height font-size]}]
  (let [m-px (-> line-height (* font-size) (js/Math.floor))]
    [[:.application {:min-height (vh 100)
                     :width "100%"
                     :padding (px m-px)
                     :display "grid"
                     :grid-template-rows "25px 1fr 25px"
                     :grid-template-columns "fit-content(20ch) minmax(min(50vw, 30ch), 1fr) fit-content(20ch)"
                     :grid-template-areas "\"header header header\" \"left   main   right\" \"footer footer footer\""
                     :gap (px m-px)}
      [(s/> :.sidebar-left) {:grid-area :left}]
      [(s/> :.sidebar-right) {:grid-area :right}]
      [(s/> :footer) {:grid-area :footer}]
      [(s/> :header) {:grid-area :header}]
      [(s/> :main) {:grid-area :main}]]
     [:.utility-application-demo {:height (px 500)}]
     [:.utility-fill-color {:background-color "rgba(150, 200, 255, 0.5)" :width "100%" :height "100%"}]]))

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
   
   The example below illustrates all of these options: a layout with a header, footer, and both sidebars"
  [:div.application.utility-application-demo.utility-fill-color
   [:header.utility-fill-color "This is a navbar"]
   [:section.sidebar-left.utility-fill-color "Left sidebar 1"]
   [:main.utility-fill-color "This is my content!"]
   [:section.sidebar-right.utility-fill-color "Right sidebar"]
   [:footer.utility-fill-color "This is a footer"]])