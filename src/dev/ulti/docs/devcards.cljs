(ns ulti.docs.devcards
  (:require [devcards.core :refer [start-devcard-ui! reagent]]
            [reagent.core :as r]
            [ulti.core :refer [themed-stylesheet]]))

(def docs-theme (r/atom {}))

(defn styled-reagent
  ([el-or-fn]
   (styled-reagent el-or-fn @docs-theme))
  ([el-or-fn theme]
  (reagent
   (fn [data-atom owner]
     [:<>
      [themed-stylesheet theme]
      (if (fn? el-or-fn)
        (el-or-fn data-atom owner)
        el-or-fn)]))))

(defn init! []
  (start-devcard-ui!)
  (println "Deleting com-rigsomelight-devcards-addons-css element...")
  (when (js/document.getElementById "com-rigsomelight-devcards-addons-css")
    (.remove (js/document.getElementById "com-rigsomelight-devcards-addons-css"))))

