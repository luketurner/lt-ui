(ns lt-ui.typography
  (:require [garden.units :refer [rem px percent em]]
            [garden.selectors :refer [descendant]]))

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
     [:a :b :i :strong :em :small :code {:line-height :inherit}]
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