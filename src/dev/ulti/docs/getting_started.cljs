(ns ulti.docs.getting-started
  (:require [devcards.core :refer [defcard]]))

(defcard
  "# Installation
   
   **Warning**: This project is under active development, and versions are provided with no
   guarantees of stability or durability. (In other words, I'm not ready to commit to semver
   for this project yet.)

   Add the following to your project dependencies:
   
   ```clojure
   [lt/ulti \"1.0.0-SNAPSHOT\"]
   ```")

(defcard
  "# Getting Started
   
   Many features of the framework depend on certain CSS rules being declared.
   Instead of packaging these as a .css file, the rules are packaged into a
   `themed-stylesheet` component that you should insert into your app:
   
   ``` clojure
   (require '[ulti.core :refer [themed-stylesheet]])

   (defn app []
     [:<>
       [themed-stylesheet { ... theme options ... }]
       [:div.app-container
        ... ]])
   ```
   
   This renders a `<style>` block with dynamically generated CSS. If you store the theme
   in a Reagent atom, the stylesheet will reactively update when the theme changes.")

(defcard
  "# Installing from Source
   
   This section convers installing `ulti` locally. This is only necessary if you want to edit
   the `ulti` source code.

   Requirements:

   - node
   - npm
   - git
   - leiningen

   Setup:

   ``` bash
   git clone https://git.sr.ht/~luketurner/ulti
   cd ulti
   npm i
   ```

   Scripts for local development:

   ``` bash
   npm run library:local # publishes library to ~/.m2
   npm run docs:start    # launches docs
   npm run docs:build    # builds docs into ./public
   ```")