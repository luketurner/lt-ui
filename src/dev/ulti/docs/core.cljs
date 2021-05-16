(ns ulti.docs.core
  (:require [ulti.docs.devcards :as devcards]
            [ulti.docs.containers]
            [ulti.docs.forms]
            [ulti.docs.icons]
            [ulti.docs.inputs]
            [ulti.docs.--START-HERE--]
            [ulti.docs.themes]
            [ulti.docs.typography]
            [ulti.icons :refer [set-default-spritesheet! set-default-icon-prefix!]]))

(defn init! []
  (set-default-spritesheet! "static/tabler-sprite.svg")
  (set-default-icon-prefix! "tabler-")
  (devcards/init!))

