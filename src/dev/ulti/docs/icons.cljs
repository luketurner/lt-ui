(ns ulti.docs.icons
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [devcards.core :refer [defcard]]
            [ulti.icons :as icons]))

(defcard
  "# Icons
   
   Ulti provides a flexible icon framework that's BYOI (Bring Your Own Icons).
   This means there are no icons packaged with the framework; you need to provide
   the icons yourself (as a static asset, or embedded in the page).
   
   Your icons must be provided in the form of SVG `<symbol>` declarations. For example,
   if you want to use the `search` icon, you have to define:
  
   ```
   <symbol id=\"search\">
     <!-- my SVG stuff here -->
   </symbol>
   ```

   Within the `icon` component, an SVG `<use>` element pulls in the requested icon from
   where the symbol is defined.

   The reason Ulti doesn't package icons directly is there's no convenient way (I can see)
   for a library consumer to expose a library's static assets when their project is compiled.
   Eventually, I'd like to include a selection of item packs that are either preinstalled or
   can be installed automatically.

   Currently the only icon pack that's tested (in these docs) is
   https://github.com/tabler/tabler-icons.
   
   Assuming you have a `/public/static/tabler-sprite.svg` file in your project:

   ``` clojure
   (set-default-spritesheet! \"static/tabler-sprite.svg\")
   (set-default-icon-prefix! \"tabler-\")
   ```
   
   Then, you can freely use the `icon` component in your app.")

(defcard' icon
  "The icon component! Pass an `:icon` prop with a keyword argument that specifies the name of the icon you wanna use -- in this case, `:search`."
  [icons/icon {:icon :search}])

;; (defcard
;;   "The following icons are available by default:"
;;   (keys (:svg icons/default-icons)))