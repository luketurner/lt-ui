(ns lt-ui.docs.getting-started
  (:require [devcards.core :refer [defcard]]))

(defcard
  "# Installation
   
   This framework is currently under development for personal use. It is not production-ready
   and the installation mechanism is not secure (as you can see for yourself.)
   
   **Unless you're me, don't follow these instructions!**

   Okay, you're me? Everyone else is gone? Great.
   
   You can use this unsafely by adding a custom repository to your project's dependency file:
   
   ``` bash
   :repositories {\"ltui\" {:url \"https://raw.githubusercontent.com/luketurner/lt-ui/master/dist\"
                          :checksum :warn}}
   ```

   (The `:checksum :warn` thing is necessary due to the way Git turns my CRLFs into LFs... I think.)
   
   Then reference in your project with:
   
   ```clojure
   [lt-ui \"0.0.1\"]
   ```")

(defcard
  "# Getting Started
   
   Many features of the framework depend on certain CSS rules being declared.
   Instead of packaging these as a .css file, the rules are packaged into a
   `themed-stylesheet` component that you should insert into your app:
   
   ``` clojure
   (require '[lt-ui.core :refer [themed-stylesheet]])

   (defn app []
     [:<>
       [themed-stylesheet { ... theme options ... }]
       [:div.app-container
        ... ]])
   ```
   
   This renders a `<style>` block with dynamically generated CSS. If you store the theme
   in a Reagent atom, the stylesheet will reactively update when the theme changes.")