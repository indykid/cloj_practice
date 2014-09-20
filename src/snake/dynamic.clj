(ns snake.dynamic
  (:require [quil.core :as q])
  (:import java.awt.event.KeyEvent))

(def red 1)
(def orange 25)
(def yellow 50)
(def green 100)
(def blue 150)
(def purple 200)

(def window-size 800)
(def grid-size 50)
(def cell-size (/ window-size grid-size))

(def snake (atom nil))

(defn reset-snake []
  (reset! snake
          [[0 0]
           [0 1]
           [0 2]]))

(reset-snake)

(defn +mod [x y]
  (mod (+ x y) grid-size))

(defn vec+ [[x1 y1]
            [x2 y2]]
  [(+mod x1 x2) (+mod y1 y2)])

(def up [0 -1])
(def down [0 1])
(def left [-1 0])
(def right [1 0])
(def direction (atom up))

(defn update-snake [snake direction]
  (concat (rest snake)
          [(vec+ (last snake) direction)]))

(defn move [snake direction]
  (swap! snake update-snake direction))

(defn draw-cell [[x y] hue]
  (q/fill (q/color hue 255 255))
  (q/rect (* cell-size x) (* cell-size y) cell-size cell-size))

(defn setup []
  (q/smooth)
  (q/color-mode :hsb)
  (q/frame-rate 10))

(def direction-from-key {
                         KeyEvent/VK_UP up
                         KeyEvent/VK_DOWN down
                         KeyEvent/VK_LEFT left
                         KeyEvent/VK_RIGHT right
                         \w up
                         \s down
                         \a left
                         \d right})

(defn key-pressed []
  (let [key (q/raw-key)]
    (println key)
    (if-let [new-direction (direction-from-key key)]
      (reset! direction new-direction))))

(defn draw []
  (q/background 0)
  (move snake @direction)
  (dosync
   (doseq [cell @snake]
     (draw-cell cell red))))