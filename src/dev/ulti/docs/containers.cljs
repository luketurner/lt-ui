(ns ulti.docs.containers
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [ulti.containers :as containers]))

(defcard' application-container-overview
  "The `application` component provides a top-level container for conventional Web application layouts.
   
   The application container has five visual areas:

   - Main Content
   - Header
   - Footer
   - Left Sidebar
   - Right Sidebar

   Simplest usage is like this:

   ``` clojure
   [application [:p \"my content\"]]
   ```

   

   All the elements are separated by a consistent gutter (which is calculated from the line-height of your theme.)
   
   The example below illustrates all of these options: a layout with a header, footer, and both sidebars.
   Additional classes are added to help visualize the relation of the areas."
  [containers/application {:class "utility-application-demo"
                           :header [:p.centered.utility-fill-color "Header"]
                           :sidebar-left [:p.centered.utility-fill-color "Left Sidebar"]
                           :sidebar-right [:p.centered.utility-fill-color "Right Sidebar"]
                           :footer [:p.centered.utility-fill-color "Footer"]}
   [:p.centered.utility-fill-color "Content"]])

(defcard' router
  "A `router` component is configured with multiple views, and chooses which view to use based on the
   value of the `value` prop provided to it:
   
   ``` clojure
   [containers/router {:value :foo
                       :views {:foo [:p \"Foo view\"]
                               :bar [:p \"Bar view\"]}}]
   ```
   
   The router has no internal state -- it's externally controlled by setting the `:value` property, just like an input.
   Unlike inputs however, it has no `on-change` handler."
  (fn [data-atom]
    (let [page @data-atom
          open (fn [page] (fn [] (reset! data-atom page)))]
      [:<>
       [containers/router {:value page
                           :views {:home [:p "Home view"]
                                   :help [:p "Help view"]
                                   :settings [:p "Settings view"]}}]
       [:div
        [:button {:on-click (open :home)} "Home"]
        [:button {:on-click (open :help)} "Help"]
        [:button {:on-click (open :settings)} "Settings"]]]))
  :home
  {:inspect-data true})

(defcard' centered 
  "A container that centers its content."
  [containers/centered
   [:button "This button is centered"]])