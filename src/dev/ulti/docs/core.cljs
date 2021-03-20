(ns ulti.docs.core
  (:require [ulti.docs.devcards :as devcards]
            [ulti.docs.containers]
            [ulti.docs.forms]
            [ulti.docs.inputs]
            [ulti.docs.getting-started]
            [ulti.docs.themes]
            [ulti.docs.typography]))

(defn init! []
  (devcards/init!))

