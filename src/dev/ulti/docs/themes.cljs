(ns ulti.docs.themes
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [ulti.core :refer [default-theme]]
            [ulti.containers :as containers]
            [devcards.core :refer [defcard]]))

(defcard
  "# Themes
   
   Ulti styles can be adjusted with high-level configuration called a **theme**.
   Themes are used by passing them as a parameter to the `themed-stylesheet` component.
   
   To allow the theme to dynamically change, store it in a Reagent atom
   (and deref before passing to `themed-stylesheet`).
   
   All parts of the theme are optional. Omitted options will take on the default value
   indicated below:"
  default-theme)
