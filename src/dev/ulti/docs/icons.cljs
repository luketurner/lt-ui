(ns ulti.docs.icons
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [devcards.core :refer [defcard]]
            [ulti.icons :as icons]))

(defcard' icon-svg-provider
  "In `ulti`, icons are included as SVG elements.
   Instead of having the icon's SVG (which could be quite large) repeated for each element
   on the page, the icon's SVG is encapsulated in a `<symbol>` and referenced within each icon
   component by `<use>`.

   This means that the SVG for each symbol is only included once. It also means that somewhere on the page, you need to include an SVG block to *define*
   the symbols we want to use.
   
   For Ulti, this can be done with the `icon-svg-provider` component. This component simply has
   to be rendered somewhere in your DOM, and then the `icon` component will work!
   
   (This example actually renders an `icon-svg-provider`, but because it's invisible, there's nothing to see!)"
  [icons/icon-svg-provider])

(defcard' icon
  "The icon component! Pass an `:icon` prop with a keyword argument that specifies the name of the icon you wanna use -- in this case, `:search`."
  [icons/icon {:icon :search}])

(defcard
  "The following icons are available by default:"
  (keys (:svg icons/default-icons)))