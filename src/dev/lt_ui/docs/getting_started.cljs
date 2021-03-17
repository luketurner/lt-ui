(ns lt-ui.docs.getting-started
  (:require [devcards.core :refer [defcard]]))

(defcard
  "# Installation
   
   Since this framework is currently under development for personal use, it's not
   published to Clojars.
   
   In the meantime, the following suffices to reference the project locally:
   
   ``` bash
   git clone https://git.sr.ht/~luketurner/lt-ui
   cd lt-ui
   lein deploy local # deploys to ~/.m2
   ```
   
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