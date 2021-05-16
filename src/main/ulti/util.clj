(ns ulti.util)

(defmacro defcomponent
  "Convenience macro for defining components.
   
   Components can be called flexibly with children and props both
   optional. The following forms are supported:
   
   No children or props: [mycomponent]
   Children and no props: [mycomponent child1 child2]
   Props and no children: [mycomponent {myprops}]
   Props and children: [mycomponent {myprops} child1 child2]
   Using :children prop: [mycomponent {myprops :children [child1 child2]}]
   
   No matter what form is used to call the component, the `args` presented
   to the component definition will be a single map of props, which also
   includes a :children key containing any children and a :props key that
   contains all the props not explicitly named in a :keys destructuring.
   That might not make any sense, try checking out some examples where this
   function is used."
  [name args & forms]
  (let [prop-kws (->> args first :keys (map keyword))]
  `(defn ~name [x# & xs#]
     (let [children# (if (map? x#) xs# (into [x#] xs#))
           props# (cond-> (if (map? x#) x# {})
                   (seq children#) (assoc :children children#)
                   true (#(assoc % :props (dissoc % ~@prop-kws))))
           ~args [props#]]
       ~@forms))))