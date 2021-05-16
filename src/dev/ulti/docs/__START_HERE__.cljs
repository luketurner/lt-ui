(ns ulti.docs.--START-HERE--
  (:require [devcards.core :refer [defcard]]))

(defcard
  "# What is this?
   
   **Ulti is a toolkit for building themeable Web UIs**. It includes CSS and React components.
   Ulti is written in ClojureScript and only supports ClojureScript projects.

   Ulti is a personal project intended to enable me to be lazy and reuse UI components across
   CLJS projects. The name hints at this personal intent; `ulti` is an anagram of `lt-ui`.
   I've published it as a library with documentation to inspire others, _not_ because I expect
   Ulti to be broadly usable.
   
   **What you're reading now is Ulti's documentation**. It's written using the Devcards
   library, and includes interactive examples and descriptions for all Ulti's supported
   features. ([Go to documentation home](#!))
   
   Ulti can be installed using Clojars (see Installation section below).")

(defcard
  "# Installation
   
   **Warning**: This project is under active development, and versions are provided with no
   guarantees of stability or durability. (In other words, I'm not ready to commit to semver
   for this project yet.)

   Add the following to your project dependencies:
   
   ```clojure
   [lt/ulti \"1.0.0-SNAPSHOT\"]
   ```
   
   Additionally, **this project has `npm` dependencies that must be installed for it to work**.
   These are declared in `src/main/deps.cljs`.
   If you're using `shadow-cljs`, they will be installed automatically.
   Other build tooling is untested.
   If your build tooling doesn't support NPM modules, you will have to install them manually.

   ## Icons
   
   Some features will require installing a third party icon-pack. Ulti doesn't distribute
   one due to a combination of laziness and technical limitations, but I call it a feature because
   you can pick your icons to match your theme.

   See the [icons page](#!/ulti.docs.icons) for details about how to install and use Tabler Icons,
   an MIT-licensed icon pack.
   (You probably want to read the Getting Started section below first, though.)")

(defcard
  "# Getting Started
   
   Ulti provides a variety of React components and helper functions. They're grouped into
   a few namespaces:

   - **ulti.core**: global configuration and setup functions (documented below)
   - [ulti.containers](#!/ulti.docs.containers): Components that contain content within themselves, wrapping it with
     some functionality. (layouts, splitters, popovers, etc.) 
   - [ulti.forms](#!/ulti.docs.forms): Components/functions for grouping inputs together into submittable forms.
   - [ulti.icons](#!/ulti.docs.icons): A BYOI (Bring Your Own Icons) module based on SVG spritemaps.
   - [ulti.inputs](#!/ulti.docs.inputs): Components/functions for creating inputs and composing them in reusable ways.
   - [ulti.typography](#!/ulti.docs.typography): Calculates CSS rules for a typography system a la Gridlover. (Doesn't need
     to be directly imported ever.)

   Generally, it's expected that you'll mix-and-match depending on your project's needs. 

   Many features of the framework depend on certain CSS rules being declared.
   Instead of packaging these as a .css file, the rules are packaged into a
   `themed-stylesheet` component that you should insert into your app somewhere it will always
   be rendered:
   
   ```clojure
   (require '[ulti.core :refer [themed-stylesheet]])

   (defn app []
     [:<>
       [themed-stylesheet { ... theme options ... }]
       [:div.app-container
        ... ]])
   ```
   
   By default, this includes the CSS for all Ulti components, including the typography system.
   This affects the styling of default elements like `<p>`, `<h1>`, etc. There's no option to
   turn this off, just something to be aware of.

   **Note:** This component renders a `<style>` block with dynamically generated CSS.
   If you store the theme in a Reagent atom, the stylesheet will reactively update
   when the theme is changed.")

(defcard
  "# Building from source
   
   This section convers building `ulti` locally. This is only necessary if you want to edit
   the `ulti` source code (or you don't want to use Clojars, I guess.)
   
   Requirements:

   - node
   - npm
   - git
   - leiningen

   Setup:

   ```bash
   git clone https://git.sr.ht/~luketurner/ulti
   cd ulti
   npm i
   ```

   Scripts for local development:

   ```bash
   npm run library:local # publishes library to ~/.m2
   npm run docs:start    # launches docs
   npm run docs:build    # builds docs into ./public
   ```")