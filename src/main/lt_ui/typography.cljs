(ns lt-ui.typography
  (:require [garden.units :refer [rem px percent em]]
            [garden.selectors :refer [descendant]]
            [lt-ui.devcards :refer [defcard']]))

(defn rules
  "Defines rules for typography. They boil down to:
   
   1. Set the base font size to :font-size.
   2. Set line height to (* :line-height :font-size)
   3. Adjust common elements to be sized, margined, etc. in increments of the line height (vertical rhythm)
   4. When elements are larger/smaller, the :scale-factor is used to adjust their font size.
   
   Based on https://www.gridlover.net/"
  [{fs-px :font-size lh-ratio :line-height sf :scale-factor}]
  ; Note -- values should be interpreted as rems unless ending in -px or -ratio
  (let [lh-px (-> lh-ratio (* fs-px) (js/Math.floor))
        lh (/ lh-px fs-px)
        sf2 (* sf sf)
        sf3 (* sf sf sf)]
    [[:html {:font-size (px fs-px) :line-height (px lh-px)}]
     [:body {:line-height (rem lh)}]
     [:h1 {:font-size (rem sf3) :line-height (rem (* lh (js/Math.round sf3))) :margin-top (rem lh) :margin-bottom (rem (* 2 lh))}]
     [:h2 {:font-size (rem sf2) :line-height (rem (* lh (js/Math.round sf2))) :margin-top (rem lh) :margin-bottom (rem lh)}]
     [:h3 {:font-size (rem sf) :line-height (rem (* lh (js/Math.round sf))) :margin-top (rem lh) :margin-bottom 0}]
     [:h4 {:font-size (rem 1) :line-height (rem lh) :margin-top (rem lh) :margin-bottom 0}]
     [:h5 {:font-size (rem 1) :line-height (rem lh) :margin-top (rem lh) :margin-bottom 0}]
     [:p :ul :ol :dl :pre :table :blockquote {:margin-top 0 :margin-bottom (rem lh)}]
     [(descendant :ul :ul) (descendant :ul :ol) (descendant :ol :ul) (descendant :ol :ol) {:margin-top 0 :margin-bottom 0}]
     [:hr {:border "1px solid" :margin "-1px 0"}]
     [:a :b :i :strong :em :small :code {:line-height 0}]
     [:sub :sup {:line-height 0 :position :relative :vertical-align :baseline}]
     [:sup {:top (em -0.5)}]
     [:sub {:bottom (em -0.25)}]
     [:.utility-show-rows {:display :block
                           :position :absolute
                           :left (px 0)
                           :right (px 0)
                           :pointer-events :none
                           :user-select :none
                           :top (px 0)
                           :height (percent 100)
                           :background (str "rgba(0, 0, 0, 0) linear-gradient(rgba(0, 119, 179, 0.2) 1px, transparent 1px) repeat scroll left top / " lh-px "px " lh-px "px")}]]))

(defcard' [:h1 "Heading One"])
(defcard' [:h2 "Heading Two"])
(defcard' [:h3 "Heading Three"])
(defcard' [:h4 "Heading Four"])
(defcard' [:h5 "Heading Five"])
(defcard' [:<>
           [:div.utility-show-rows]
           [:h1 "Typography"]
           [:h2 "Heading Two"]
           [:h3 "Heading Three"]
           [:h4 "Heading Four"]
           [:h5 "Heading Five"]
           [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lacinia felis at efficitur. Vivamus venenatis quis libero non consequat. Mauris hendrerit, dolor eget ultrices consectetur, sapien neque pharetra ante, a laoreet ex felis vel velit. In gravida sapien ligula, non finibus augue facilisis ut. Nam vel dapibus risus, nec efficitur nisl. Proin fringilla molestie dolor, eget congue diam tempor quis. Vestibulum iaculis, nisl at rhoncus dignissim, sem ligula accumsan nibh, a facilisis nunc lorem in ante. Quisque auctor lacus sit amet risus maximus, vulputate sollicitudin dui rhoncus."]
           [:p "Praesent ex sem, lacinia id luctus ut, imperdiet in lacus. Aliquam erat volutpat. Nullam vel dignissim eros. Ut non facilisis felis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam bibendum at odio ut auctor. Sed ut vestibulum diam, non scelerisque quam. Duis id felis aliquam enim lacinia ultricies quis vitae eros. Pellentesque et lacinia magna. Pellentesque dignissim pellentesque laoreet. Quisque quis posuere dui. Integer congue, justo ac venenatis finibus, tortor ligula facilisis tortor, vitae accumsan felis libero vel elit. Donec in nunc ligula. Fusce pharetra ante mattis turpis interdum, eget molestie risus vestibulum. Pellentesque rutrum euismod urna, non facilisis orci sodales ac. Etiam venenatis ipsum urna, id volutpat nulla posuere at."]
           [:p "Supertext:" [:sup "Superb!"]]
           [:p "Subtext:" [:sub "Subtle!"]]
           [:ul
            [:li "List item one"]
            [:li "List item two"]
            [:ul
             [:li "nested item"]
             [:li "nested item two"]]]
           [:ol
            [:li "List item one"]
            [:li "List item two"]
            [:ol
             [:li "nested item"]
             [:li "nested item two"]]]
           [:dl
            [:dt "Title one"]
            [:dd "Description one"]
            [:dt "Title two"]
            [:dd "Description two"]]

           [:p "Horizontal rules line up as well:"]
           [:hr]

           [:blockquote
            [:p "This is a blockquote!"]]

           [:pre
            "function () {
  return 'This is a code block!';
}"]])
