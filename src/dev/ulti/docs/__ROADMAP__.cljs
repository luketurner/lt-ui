(ns ulti.docs.--ROADMAP--
  (:require [devcards.core :refer [defcard]]))

(defcard
  "# Roadmap

  **NOTE:** Ulti is not being actively developed anymore.
   
   This page lists the features that will be present in Ulti
   before release. Not all features are implemented yet.

   ## Global styles

   These styles are globally applied across the whole site. They
   set sane defaults for elements like `<p>` and `<a>`, ensuring
   the look-and-feel is consistent with your theme.

   - Typography: Basic typographical styling (vertical rhythm, etc.)
     is automatically applied by default.
   
   ## Component library

   The following React components are available:

   ### Containers

   Containers are components that encapsulate one or more child elements,
   wrapping them with some behavior or styles.

   #### Layout containers

   Layout containers organize how their children are laid out
   in relation to each other.

   - Application layout: A layout with header, footer, sidebars, and
   main content area.
   - Center: Content is centered
   - Box: Content is given some space
   - Splitters: Content is horizontally/vertically split
   - Stack: Content is stacked on top of each other
   - Flow: Content is flowed along the same line

   #### Visual containers

   - Paper
   - Card
   

   - Router: Conditionally render something or another
   - Vertical/horizontal splitters
   - Centered content
   - Paper/crater: Paper makes the content sit on top like paper,
   crater makes the content sit below like a crater.
   - Hotkey zone: Define hotkeys
   - Popover: The content appears overtop other content when a handler
     is clicked.
   - Menu: Define custom menus with submenus
   - Box: Keeps itself a reasonable size even if it's not got much in it
   - Tables
   - Panel Stack?
   - Collapsibles
   - Resizables
   - Tabs
   - Trees?
   - Portal: Renders its children somewhere else in the DOM tree.
   - Modal frame: makes content steal focus from rest of the page, and
   prevents returning to the rest of the page until modal frame is closed.
   - Dialog? (note, could be embedded in a wizard too)
   
   ### Forms

   ### Inputs

   ### Other

   - Dividers
   - Buttons?
   - Breadcrumbs?
   - Icons
   - Cards?
   - Tags?
   - Text with ellipses
   - Tooltips?
   - Help text
   - Figures?
   - Toasts

   ## Utility classes

   Ulti also exposes utility classes for writing your own components.")