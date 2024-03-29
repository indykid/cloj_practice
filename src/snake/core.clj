(ns snake.core
  (:require [quil.core :as q]))

(def red 1)
(def orange 25)
(def yellow 50)
(def green 100)
(def blue 150)
(def purple 200)

(def window-size 800)
(def grid-size 10)
(def cell-size (/ window-size grid-size))

(def snake (atom [1 2 3 4 5]))

(swap! snake (fn [s]
               (map inc s)))

;@snake
;(map inc @snake)

(defn draw-cell [[x y] hue]
  (q/fill (q/color hue 255 255))
  (q/rect (* cell-size x) (* cell-size y) cell-size cell-size))

(defn setup []
  (q/smooth)
  (q/color-mode :hsb)
  (q/frame-rate 1))

(defn draw []
  (q/background 0)
  (doseq [x @snake]
  (draw-cell [1 x] red)
   )


  ; Add stuff here
  )



(q/defsketch snake-game
  :title "Snake"
  :setup setup
  :draw draw
  :size [window-size window-size]
  )

(defn -main [])
