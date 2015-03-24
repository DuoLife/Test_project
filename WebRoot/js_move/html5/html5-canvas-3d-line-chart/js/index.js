// -------------------------
// easing.js
// -------------------------

var easing = {

  // Penner's easing equations
  // http://www.kirupa.com/forum/showthread.php?378287-Robert-Penner-s-Easing-Equations-in-Pure-JS-(no-jQuery)

  linearEase: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * currentIteration / totalIterations + startValue;
  },

  easeInQuad: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (currentIteration /= totalIterations) * currentIteration + startValue;
  },

  easeOutQuad: function (currentIteration, startValue, changeInValue, totalIterations) {
    return -changeInValue * (currentIteration /= totalIterations) * (currentIteration - 2) + startValue;
  },

  easeInOutQuad: function (currentIteration, startValue, changeInValue, totalIterations) {
    if ((currentIteration /= totalIterations / 2) < 1) {
      return changeInValue / 2 * currentIteration * currentIteration + startValue;
    }
    return -changeInValue / 2 * ((--currentIteration) * (currentIteration - 2) - 1) + startValue;
  },

  easeInCubic: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * Math.pow(currentIteration / totalIterations, 3) + startValue;
  },

  easeOutCubic: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (Math.pow(currentIteration / totalIterations - 1, 3) + 1) + startValue;
  },

  easeInOutCubic: function (currentIteration, startValue, changeInValue, totalIterations) {
    if ((currentIteration /= totalIterations / 2) < 1) {
      return changeInValue / 2 * Math.pow(currentIteration, 3) + startValue;
    }
    return changeInValue / 2 * (Math.pow(currentIteration - 2, 3) + 2) + startValue;
  },

  easeInQuart: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * Math.pow (currentIteration / totalIterations, 4) + startValue;
  },

  easeOutQuart: function (currentIteration, startValue, changeInValue, totalIterations) {
    return -changeInValue * (Math.pow(currentIteration / totalIterations - 1, 4) - 1) + startValue;
  },

  easeInOutQuart: function (currentIteration, startValue, changeInValue, totalIterations) {
    if ((currentIteration /= totalIterations / 2) < 1) {
      return changeInValue / 2 * Math.pow(currentIteration, 4) + startValue;
    }
    return -changeInValue / 2 * (Math.pow(currentIteration - 2, 4) - 2) + startValue;
  },

  easeInQuint: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * Math.pow (currentIteration / totalIterations, 5) + startValue;
  },

  easeOutQuint: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (Math.pow(currentIteration / totalIterations - 1, 5) + 1) + startValue;
  },

  easeInOutQuint: function (currentIteration, startValue, changeInValue, totalIterations) {
    if ((currentIteration /= totalIterations / 2) < 1) {
      return changeInValue / 2 * Math.pow(currentIteration, 5) + startValue;
    }
    return changeInValue / 2 * (Math.pow(currentIteration - 2, 5) + 2) + startValue;
  },

  easeInSine: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (1 - Math.cos(currentIteration / totalIterations * (Math.PI / 2))) + startValue;
  },

  easeOutSine: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * Math.sin(currentIteration / totalIterations * (Math.PI / 2)) + startValue;
  },

  easeInOutSine: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue / 2 * (1 - Math.cos(Math.PI * currentIteration / totalIterations)) + startValue;
  },

  easeInExpo: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * Math.pow(2, 10 * (currentIteration / totalIterations - 1)) + startValue;
  },

  easeOutExpo: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (-Math.pow(2, -10 * currentIteration / totalIterations) + 1) + startValue;
  },

  easeInOutExpo: function (currentIteration, startValue, changeInValue, totalIterations) {
    if ((currentIteration /= totalIterations / 2) < 1) {
      return changeInValue / 2 * Math.pow(2, 10 * (currentIteration - 1)) + startValue;
    }
    return changeInValue / 2 * (-Math.pow(2, -10 * --currentIteration) + 2) + startValue;
  },

  easeInCirc: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (1 - Math.sqrt(1 - (currentIteration /= totalIterations) * currentIteration)) + startValue;
  },

  easeOutCirc: function (currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * Math.sqrt(1 - (currentIteration = currentIteration / totalIterations - 1) * currentIteration) + startValue;
  },

  easeInOutCirc: function (currentIteration, startValue, changeInValue, totalIterations) {
    if ((currentIteration /= totalIterations / 2) < 1) {
      return changeInValue / 2 * (1 - Math.sqrt(1 - currentIteration * currentIteration)) + startValue;
    }
    return changeInValue / 2 * (Math.sqrt(1 - (currentIteration -= 2) * currentIteration) + 1) + startValue;
  }

};

// -------------------------
// graph.js
// -------------------------

// shim up requestAnimationFrame
window.requestAnimationFrame = window.requestAnimationFrame ||
                               window.mozRequestAnimationFrame ||
                               window.webkitRequestAnimationFrame ||
                               window.msRequestAnimationFrame;

function Graph ( options ) {

  var canvas = document.getElementById("graph");
  var ctx = canvas.getContext("2d");
  ctx.canvas.width = window.innerWidth;

  // general settings
  var settings = {
    // number of dots
    dots: 50,
    speed: 500,
    dot: {
      count: 25,
      diameter: 4,
      maxDiameter: 50,
      spacing: 10,
      opacityArray: [],
      diameterArray: []
    },
    // rotation limits
    rotate: {
      min: -70,
      max: 70,
      flourish: {
        interval: 60 * 1000,
        speed: 10 * 1000,
        enable: true,
        split: true,
        count: 0,
        active: false
      }
    },
    // float the dots
    float: {
      diameter: 10,
      speed: 4000,
      delay: 200
    },
    // shake the dots
    shake: {
      line: 0,
      dot: 0
    },
    // canvas padding value
    padding: 25,
    // how much fake data y can fluctuate from one point to the next
    delta: 5,
    // show fps
    fps: {
      show: false,
      last: 0
    }
  };

  // cache fps counter
  var fps = document.getElementById("fps");

  // keep dots within upper and lower bounds
  settings.y = {
    max: 0 + settings.padding,
    min: canvas.height - settings.padding
  };

  // compute inner width and height of canvas
  settings.innerWidth = canvas.width - (settings.padding * 2);
  settings.innerHeight = settings.y.max - settings.y.min;

  // height of dot line
  settings.dot.height = settings.dot.spacing * (settings.dot.count - 1);

  // set up dot line diameter and opacity array
  var middle = Math.floor(settings.dot.count / 2) + 1;
  for (var i = 0; i < middle; i++) {
    settings.dot.diameterArray.push( easing.easeInQuad( i, settings.dot.diameter, settings.dot.maxDiameter, middle - 1 ) );
    settings.dot.opacityArray.push( easing.easeInCubic( i, 0, 0.6, middle - 1 ) );
  }
  settings.dot.diameterArray = mirrorArray( settings.dot.diameterArray.reverse() );
  settings.dot.opacityArray = mirrorArray( settings.dot.opacityArray );

  // set up dots array
  var dots = createDots(settings);

  // keep sample values in an array by themselves
  var values = [];

  // kick things off
  this.init = function () {
    window.requestAnimationFrame(handleTick);
  };

  // listen for sample event on the #source element
  $("#source").on("sample", function(e, o) {
    setPoint(o.index, o.value);
  });

  // listen for reset event on the #source element
  $("#source").on("reset", function(e) {
    resetDots(dots);
  });

  // set the y of the current point when fake data arrives
  function setPoint (i, v) {
    if (i < settings.dots) {
      // push v on to values array
      if (i === 0) {
        values.length = 0;
      }
      values.push(v);
      // compute value min and max (assume 0 min unless lower is present)
      var vmin = Math.min(0, Math.min.apply(null, values));
      var vmax = Math.max.apply(null, values);
      for ( var index = 0; index <= i; index++) {
        // scale v to y max and min using:
        // NewValue = (((OldValue - OldMin) * (NewMax - NewMin)) / (OldMax - OldMin)) + NewMin
        var y;
        var yrange = settings.y.max - settings.y.min;
        var vrange = (vmax - vmin);
        // avoid divide by 0 error by giving v an arbitrary range
        if (vrange === 0) {
          vrange = 1;
        }
        y = ((( values[index] - vmin ) * yrange ) / vrange ) + settings.y.min;
        // send the dot to the new y
        dots[index].goal = y;
        dots[index].active = true;
      }
    }
  }

  // animation frame
  function handleTick (timestamp) {
    if (settings.fps.show) {
      fps.innerHTML = 1000 / (timestamp - settings.fps.last);
      settings.fps.last = timestamp;
    }
    // clear the canvas
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    // draw each dot
    var x = 0;
    var y = -1 * settings.dot.height / 2;
    var rotationCounter = 0;
    for (var i = 0; i < settings.dots; i++) {
      var currentY = dots[i].y;
      if (dots[i].goal !== currentY) {
        if (dots[i].time.start === 0) {
          dots[i].time.start = timestamp;
        }
        currentY = easing.easeInOutCubic(timestamp - dots[i].time.start, dots[i].y, dots[i].goal - dots[i].y, settings.speed);
        if (timestamp - dots[i].time.start >= settings.speed) {
          dots[i].y = dots[i].goal;
          dots[i].time.start = 0;
          dots[i].y = dots[i].goal;
        }
      }
      // save canvas position
      ctx.save();
      // figure out how much to float this dot
      var float = easing.easeInOutSine( (timestamp - settings.float.delay * i) % (settings.float.speed * 2), -settings.float.diameter / 2, settings.float.diameter, settings.float.speed );
      // translate canvas to center point of dot
      var translateX = dots[i].x + fluctuate(settings.shake.line);
      var translateY = currentY + fluctuate(settings.shake.line) + float;
      ctx.translate(translateX, translateY);
      // rotate the canvas based on dot's rotation
      var rotation = dots[i].rotation;
      if ( settings.rotate.flourish.enable ) {
        if ( settings.rotate.flourish.active ) {
          if ( timestamp % settings.rotate.flourish.interval >= settings.rotate.flourish.speed ) {
            settings.rotate.flourish.active = false;
          } else {
            var direction = (i < settings.dots / 2) ? 360 : -360;
            if (!settings.rotate.flourish.split) {
              direction = Math.abs(direction);
            }
            rotation = easing.easeInOutQuad( timestamp % settings.rotate.flourish.speed, rotation, direction, settings.rotate.flourish.speed );
          }
        } else {
          if ( timestamp / settings.rotate.flourish.interval > settings.rotate.flourish.count + 1 ) {
            settings.rotate.flourish.count++;
            settings.rotate.flourish.active = true;
          }
        }
      }
      rotation *= Math.PI / 180;
      ctx.rotate(rotation);
      // draw the dot
      drawDot(ctx, x, y, i);
      // restore the canvas to the saved position
      ctx.restore();
    }
    // request another frame
    window.requestAnimationFrame(handleTick);
  }

  // draw a line of dots
  function drawDot (ctx, x, y, index) {
    for (var i = 0; i < settings.dot.count; i++) {
      var diameter = settings.dot.diameterArray[i];
      ctx.beginPath();
      ctx.arc(x + fluctuate(settings.shake.dot), y + i * settings.dot.spacing + fluctuate(settings.shake.dot), diameter / 2, 0, 2 * Math.PI, false);
      var color = "16, 116, 151";
      var opacity = settings.dot.opacityArray[i];
      // check to see if this is the middle dot
      if (i == middle - 1) {
        if ( dots[index].active ) {
          color = "258, 168, 1";
        }
        opacity = 1;
      }
      ctx.fillStyle = "rgba(" + color + ", " + opacity + ")";
      ctx.fill();
    }
  }

  // randomly raise or lower a value based on a delta
  function fluctuate (delta) {
    return delta / 2 - delta * Math.random();
  }

  // taken an array and append a mirror of itself
  function mirrorArray (a) {
    var c = a.slice(0);
    if ( (a.length % 2) ) {
      c.length = c.length - 1;
    }
    a.push.apply(a, c.reverse());
    return a;
  }

  function createDots(settings) {
    var dots = [];
    for (var i = 0; i < settings.dots; i++) {
      // give starting points a subtle variation
      var y = settings.y.min + fluctuate(settings.delta);
      dots.push({
        active: false,
        time: {
          start: 0,
          duration: settings.speed
        },
        index: i,
        x: easing.linearEase( i, settings.padding, canvas.width - settings.padding * 2, settings.dots - 1),
        init: y,
        y: y,
        rotation: easing.linearEase( i, settings.rotate.max, settings.rotate.min - settings.rotate.max, settings.dots - 1)
      });
    }
    dots = resetDots(dots);
    return dots;
  }

  function resetDots(dots) {
    for (var i = 0, len = dots.length; i < len; i++) {
      dots[i].goal = dots[i].init;
      dots[i].active = false;
    }
    return dots;
  }

}

var graph = new Graph( );
graph.init();

// -------------------------
// fakesource.js
// -------------------------

function FakeSource ( options ) {

  var settings = {
    $source: $('#source'),
    samples: 50,
    min: 0,
    max: 100,
    delta: 15,
    speed: {
      speed: 300,
      delta: 200
    }
  };

  this.init = function () {
    var samples = createSamples(settings.samples, settings.min, settings.max, settings.delta);
    var timeout = 0;
    console.log(samples);
    for (var i = 0; i < settings.samples; i++) {
      timeout += settings.speed.speed + fluctuate(settings.speed.delta);
      sendSample(i, samples[i], timeout);
    }
    // reset dots after 20 seconds
    setTimeout(function() {
      settings.$source.trigger("reset");
    }, 20000);
    // send new data after 30 seconds
    setTimeout(source.init, 25000);
  };

  function createSamples(count, min, max, delta) {
    var samples = [];
    for (var i = 0; i < count; i++) {
      var val;
      if ( i === 0 ) {
        val = Math.random() * max + min;
      } else {
        val = samples[i - 1] + fluctuate(delta);
      }
      // val = Math.min( Math.max( val, settings.min), settings.max);
     val = parseFloat(val.toFixed(2));
     samples.push( val );
    }
    return samples;
  }

  function sendSample(i, v, t) {
    setTimeout(function() {
      settings.$source.trigger("sample", {
        index: i,
        value: v
      });
    }, t);
  }

  // randomly raise or lower a value based on a delta
  function fluctuate (delta) {
    return delta / 2 - delta * Math.random();
  }

}

var source = new FakeSource();
source.init();
