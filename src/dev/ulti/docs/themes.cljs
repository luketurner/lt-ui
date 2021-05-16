(ns ulti.docs.themes
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [ulti.core :refer [default-theme]]
            [ulti.containers :as containers]
            [ulti.forms :as forms]
            [ulti.inputs :as inputs]
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

(def theme-demo-view
  [containers/application
   {:header [containers/paper
             [:strong "Heading"]]
    :sidebar-left [containers/paper
                   [:dl
                    [:dt "Left Sidebar"]
                    [:dd "This is where you can put navigation and blurbs and stuff."]
                    [:dt "About me"]
                    [:dd "My name is Foo."]
                    [:dt "Cool Links"]
                    [:dd
                     [:a {:href "#!/ulti.docs.themes"} "visited link"]
                     [:br]
                     [:a {:href (str "#!/" (random-uuid))} "unvisited link"]]]]}
   [:<>
    [:h1 "Typography"]
    [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lacinia felis at efficitur. Vivamus venenatis quis libero non consequat. Mauris hendrerit, dolor eget ultrices consectetur, sapien neque pharetra ante, a laoreet ex felis vel velit. In gravida sapien ligula, non finibus augue facilisis ut. Nam vel dapibus risus, nec efficitur nisl. Proin fringilla molestie dolor, eget congue diam tempor quis. Vestibulum iaculis, nisl at rhoncus dignissim, sem ligula accumsan nibh, a facilisis nunc lorem in ante. Quisque auctor lacus sit amet risus maximus, vulputate sollicitudin dui rhoncus."]
    [:p "Praesent ex sem, lacinia id luctus ut, imperdiet in lacus. Aliquam erat volutpat. Nullam vel dignissim eros. Ut non facilisis felis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam bibendum at odio ut auctor. Sed ut vestibulum diam, non scelerisque quam. Duis id felis aliquam enim lacinia ultricies quis vitae eros. Pellentesque et lacinia magna. Pellentesque dignissim pellentesque laoreet. Quisque quis posuere dui. Integer congue, justo ac venenatis finibus, tortor ligula facilisis tortor, vitae accumsan felis libero vel elit. Donec in nunc ligula. Fusce pharetra ante mattis turpis interdum, eget molestie risus vestibulum. Pellentesque rutrum euismod urna, non facilisis orci sodales ac. Etiam venenatis ipsum urna, id volutpat nulla posuere at."]
    [containers/vertical-split
     [:ul
      [:li "List item one"]
      [:li "List item two"]
      [:li "List item three"]]
     [:ol
      [:li "List item one"]
      [:li "List item two"]
      [:li "List item three"]]]]
   [containers/paper
    [:h1 "Form elements"]
    [forms/group
     {:label "Text Input"
      :inputs [[inputs/text {:value "Example text"}]]}]
    [forms/group
     {:label "Numeric Inputs"
      :inputs [[inputs/integer {:placeholder "Integer"}]
               [inputs/number {:placeholder "Floating"}]
               [inputs/numeric-range {:placeholder "Range"}]]}]
    [forms/group
     {:label "Textarea"
      :inputs [[inputs/textarea {:value "Text!"}]]}]]])

(defcard
  "# Example Themes
   
   Note -- because CSS rules aren't scoped per-card, all the example themes in this page will
   look the same. You have to **click on the card title** to open _just_ that card in order to see
   that card's specific theme.")

(defcard' default-theme
  "The default theme looks like this:"
  theme-demo-view)

(def solarized
  {:base03 "#002b36"
   :base02 "#073642"
   :base01 "#586e75"
   :base00 "#657b83"
   :base0 "#839496"
   :base1 "#93a1a1"
   :base2 "#eee8d5"
   :base3 "#fdf6e3"
   :yellow "#b58900"
   :orange "#cb4b16"
   :red "#dc322f"
   :magenta "#d33682"
   :violet "#6c71c4"
   :blue "#268bd2"
   :cyan "#2aa198"
   :green "#859900"})

(defcard' solarized-dark-theme
  "An example of a theme based on Solarized (Dark mode)"
  theme-demo-view
  nil
  {:theme {:colors {:content {:fg (:base0 solarized) :bg (:base03 solarized)}
                    :chrome {:fg (:base0 solarized) :bg (:base03 solarized)}}}})

(defcard' solarized-light-theme
  "An example of a theme based on Solarized (Light mode)"
  theme-demo-view
  nil
  {:theme {:colors {:content {:fg (:base00 solarized) :bg (:base3 solarized)}
                    :chrome {:fg (:base00 solarized) :bg (:base3 solarized)}}}})
