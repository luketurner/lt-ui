(ns lt-ui.devcards
  (:require [devcards.core :refer [start-devcard-ui! reagent]]
            [reagent.core :as r]))

(def styles (r/atom ""))

(defn styled-reagent [el-or-fn]
  (reagent
   (fn [data-atom owner]
     [:<>
      [:style @styles]
      (if (fn? el-or-fn)
        (el-or-fn data-atom owner)
        el-or-fn)])))

(defn init! []
  (start-devcard-ui!)
  (println "Deleting com-rigsomelight-devcards-addons-css element...")
  (when (js/document.getElementById "com-rigsomelight-devcards-addons-css")
    (.remove (js/document.getElementById "com-rigsomelight-devcards-addons-css"))))

