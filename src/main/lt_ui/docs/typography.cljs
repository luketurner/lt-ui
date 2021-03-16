(ns lt-ui.docs.typography
  (:require [lt-ui.devcards :refer-macros [defcard']]))

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
