/*
 Highcharts JS v7.1.1 (2019-04-09)

 (c) 2017-2019 Highsoft AS
 Authors: Jon Arild Nygard

 License: www.highcharts.com/license
*/
(function (a) {
	"object" === typeof module && module.exports ? (a["default"] = a, module.exports = a) : "function" === typeof define && define.amd ? define("highcharts/modules/venn", ["highcharts"], function (k) {
		a(k);
		a.Highcharts = k;
		return a
	}) : a("undefined" !== typeof Highcharts ? Highcharts : void 0)
})(function (a) {
	function k(a, e, r, l) {
		a.hasOwnProperty(e) || (a[e] = l.apply(null, r))
	}

	a = a ? a._modules : {};
	k(a, "mixins/draw-point.js", [], function () {
		var a = function (e) {
			var a = this, l = a.graphic, t = e.animatableAttribs, k = e.onComplete, v = e.css, C = e.renderer;
			if (a.shouldDraw()) l || (a.graphic = l = C[e.shapeType](e.shapeArgs).add(e.group)), l.css(v).attr(e.attribs).animate(t, e.isNew ? !1 : void 0, k); else if (l) {
				var w = function () {
					a.graphic = l = l.destroy();
					"function" === typeof k && k()
				};
				Object.keys(t).length ? l.animate(t, void 0, function () {
					w()
				}) : w()
			}
		};
		return function (e) {
			(e.attribs = e.attribs || {})["class"] = this.getClassName();
			a.call(this, e)
		}
	});
	k(a, "mixins/geometry.js", [], function () {
		return {
			getAngleBetweenPoints: function (a, e) {
				return Math.atan2(e.x - a.x, e.y - a.y)
			}, getCenterOfPoints: function (a) {
				var e =
					a.reduce(function (a, e) {
						a.x += e.x;
						a.y += e.y;
						return a
					}, {x: 0, y: 0});
				return {x: e.x / a.length, y: e.y / a.length}
			}, getDistanceBetweenPoints: function (a, e) {
				return Math.sqrt(Math.pow(e.x - a.x, 2) + Math.pow(e.y - a.y, 2))
			}
		}
	});
	k(a, "mixins/geometry-circles.js", [a["mixins/geometry.js"]], function (a) {
		var e = a.getAngleBetweenPoints, k = a.getCenterOfPoints, l = a.getDistanceBetweenPoints, u = function (a, d) {
			d = Math.pow(10, d);
			return Math.round(a * d) / d
		}, t = function (a) {
			if (0 >= a) throw Error("radius of circle must be a positive number.");
			return Math.PI *
				a * a
		}, v = function (a, d) {
			return a * a * Math.acos(1 - d / a) - (a - d) * Math.sqrt(d * (2 * a - d))
		}, C = function (a, d) {
			var h = l(a, d), e = a.r, m = d.r, x = [];
			if (h < e + m && h > Math.abs(e - m)) {
				var e = e * e, q = (e - m * m + h * h) / (2 * h), m = Math.sqrt(e - q * q), e = a.x, x = d.x;
				a = a.y;
				var k = d.y;
				d = e + q * (x - e) / h;
				q = a + q * (k - a) / h;
				a = m / h * -(k - a);
				h = m / h * -(x - e);
				x = [{x: u(d + a, 14), y: u(q - h, 14)}, {x: u(d - a, 14), y: u(q + h, 14)}]
			}
			return x
		}, w = function (a) {
			return a.reduce(function (a, e, m, l) {
				l = l.slice(m + 1).reduce(function (a, d, h) {
						var l = [m, h + m + 1];
						return a.concat(C(e, d).map(function (a) {
							a.indexes = l;
							return a
						}))
					},
					[]);
				return a.concat(l)
			}, [])
		}, A = function (a, d) {
			return l(a, d) <= d.r + 1e-10
		}, y = function (a, d) {
			return !d.some(function (d) {
				return !A(a, d)
			})
		};
		return {
			getAreaOfCircle: t,
			getAreaOfIntersectionBetweenCircles: function (a) {
				var d = w(a).filter(function (d) {
					return y(d, a)
				}), h;
				if (1 < d.length) {
					var m = k(d), d = d.map(function (a) {
						a.angle = e(m, a);
						return a
					}).sort(function (a, d) {
						return d.angle - a.angle
					}), r = d[d.length - 1], d = d.reduce(function (d, h) {
						var m = d.startPoint, r = k([m, h]), p = h.indexes.filter(function (a) {
							return -1 < m.indexes.indexOf(a)
						}).reduce(function (d,
											k) {
							k = a[k];
							var p = e(k, h), q = e(k, m), p = q - (q - p + (q < p ? 2 * Math.PI : 0)) / 2,
								p = l(r, {x: k.x + k.r * Math.sin(p), y: k.y + k.r * Math.cos(p)});
							k = k.r;
							p > 2 * k && (p = 2 * k);
							if (!d || d.width > p) d = {r: k, largeArc: p > k ? 1 : 0, width: p, x: h.x, y: h.y};
							return d
						}, null);
						if (p) {
							var q = p.r;
							d.arcs.push(["A", q, q, 0, p.largeArc, 1, p.x, p.y]);
							d.startPoint = h
						}
						return d
					}, {startPoint: r, arcs: []}).arcs;
					0 !== d.length && 1 !== d.length && (d.unshift(["M", r.x, r.y]), h = {center: m, d: d})
				}
				return h
			},
			getCircleCircleIntersection: C,
			getCirclesIntersectionPoints: w,
			getCircularSegmentArea: v,
			getOverlapBetweenCircles: function (a,
												d, e) {
				var h = 0;
				e < a + d && (e <= Math.abs(d - a) ? h = t(a < d ? a : d) : (h = (a * a - d * d + e * e) / (2 * e), e -= h, h = v(a, a - h) + v(d, d - e)), h = u(h, 14));
				return h
			},
			isPointInsideCircle: A,
			isPointInsideAllCircles: y,
			isPointOutsideAllCircles: function (a, d) {
				return !d.some(function (d) {
					return A(a, d)
				})
			},
			round: u
		}
	});
	k(a, "modules/venn.src.js", [a["mixins/draw-point.js"], a["mixins/geometry.js"], a["mixins/geometry-circles.js"], a["parts/Globals.js"]], function (a, e, k, l) {
		var u = l.Color, r = l.extend, v = k.getAreaOfCircle, t = k.getAreaOfIntersectionBetweenCircles,
			w = k.getCircleCircleIntersection,
			A = e.getCenterOfPoints, y = e.getDistanceBetweenPoints, m = k.getOverlapBetweenCircles, d = l.isArray,
			h = l.isNumber, p = l.isObject, M = k.isPointInsideAllCircles, x = k.isPointOutsideAllCircles,
			q = l.isString, N = l.merge, O = l.seriesType, P = function (a) {
				return Object.keys(a).map(function (b) {
					return a[b]
				})
			}, R = function (a) {
				var b = 0;
				2 === a.length && (b = a[0], a = a[1], b = m(b.r, a.r, y(b, a)));
				return b
			}, F = function (a, c) {
				return c.reduce(function (b, c) {
					var f = 0;
					1 < c.sets.length && (f = c.value, c = R(c.sets.map(function (b) {
						return a[b]
					})), c = f - c, f = Math.round(c *
						c * 1E11) / 1E11);
					return b + f
				}, 0)
			}, Q = function (a, c, g, d, f) {
				var b = a(c), z = a(g);
				f = f || 100;
				d = d || 1e-10;
				var e = g - c, k = 1, n, h;
				if (c >= g) throw Error("a must be smaller than b.");
				if (0 < b * z) throw Error("f(a) and f(b) must have opposite signs.");
				if (0 === b) n = c; else if (0 === z) n = g; else for (; k++ <= f && 0 !== h && e > d;) e = (g - c) / 2, n = c + e, h = a(n), 0 < b * h ? c = n : g = n;
				return n
			}, E = function (a, c, g) {
				var b = a + c;
				return 0 >= g ? b : v(a < c ? a : c) <= g ? 0 : Q(function (b) {
					b = m(a, c, b);
					return g - b
				}, 0, b)
			}, B = function (a) {
				return d(a.sets) && 1 === a.sets.length
			}, S = function (a, c) {
				var b =
					function (a, b) {
						return a.fx - b.fx
					}, d = function (a, b, c, f) {
					return b.map(function (b, g) {
						return a * b + c * f[g]
					})
				}, f = function (b, c) {
					c.fx = a(c);
					b[b.length - 1] = c;
					return b
				}, e = function (b) {
					var c = b[0];
					return b.map(function (b) {
						b = d(.5, c, .5, b);
						b.fx = a(b);
						return b
					})
				}, h = function (a) {
					for (var b = a.slice(0, -1).length, c = [], f = function (a, b) {
						a.sum += b[a.i];
						return a
					}, g = 0; g < b; g++) c[g] = a.reduce(f, {sum: 0, i: g}).sum / b;
					return c
				}, D = function (b, c, f, g) {
					b = d(f, b, g, c);
					b.fx = a(b);
					return b
				};
				c = function (b) {
					var c = b.length, f = Array(c + 1);
					f[0] = b;
					f[0].fx = a(b);
					for (var g = 0; g < c; ++g) {
						var d = b.slice();
						d[g] = d[g] ? 1.05 * d[g] : .001;
						d.fx = a(d);
						f[g + 1] = d
					}
					return f
				}(c);
				for (var k = 0; 100 > k; k++) {
					c.sort(b);
					var n = c[c.length - 1], l = h(c), m = D(l, n, 2, -1);
					m.fx < c[0].fx ? (n = D(l, n, 3, -2), c = f(c, n.fx < m.fx ? n : m)) : m.fx >= c[c.length - 2].fx ? m.fx > n.fx ? (l = D(l, n, .5, .5), c = l.fx < n.fx ? f(c, l) : e(c)) : (l = D(l, n, 1.5, -.5), c = l.fx < m.fx ? f(c, l) : e(c)) : c = f(c, m)
				}
				return c[0]
			}, G = function (a, c, g) {
				c = c.reduce(function (b, c) {
					c = c.r - y(a, c);
					return c <= b ? c : b
				}, Number.MAX_VALUE);
				return c = g.reduce(function (b, c) {
					c = y(a, c) - c.r;
					return c <=
					b ? c : b
				}, c)
			}, T = function (a, c) {
				var b = a.reduce(function (b, f) {
					var g = f.r / 2;
					return [{x: f.x, y: f.y}, {x: f.x + g, y: f.y}, {x: f.x - g, y: f.y}, {x: f.x, y: f.y + g}, {
						x: f.x,
						y: f.y - g
					}].reduce(function (b, g) {
						var f = G(g, a, c);
						b.margin < f && (b.point = g, b.margin = f);
						return b
					}, b)
				}, {point: void 0, margin: -Number.MAX_VALUE}).point, b = S(function (b) {
					return -G({x: b[0], y: b[1]}, a, c)
				}, [b.x, b.y]), b = {x: b[0], y: b[1]};
				M(b, a) && x(b, c) || (b = A(a));
				return b
			}, U = function (a) {
				var b = a.filter(B);
				return a.reduce(function (a, c) {
					if (c.value) {
						var f = c.sets;
						c = f.join();
						var g =
							b.reduce(function (a, b) {
								var c = -1 < f.indexOf(b.sets[0]);
								a[c ? "internal" : "external"].push(b.circle);
								return a
							}, {internal: [], external: []});
						a[c] = T(g.internal, g.external)
					}
					return a
				}, {})
			}, H = function (a) {
				var b = a.filter(function (a) {
					return 2 === a.sets.length
				}).reduce(function (a, b) {
					b.sets.forEach(function (c, g, d) {
						p(a[c]) || (a[c] = {overlapping: {}, totalOverlap: 0});
						a[c].totalOverlap += b.value;
						a[c].overlapping[d[1 - g]] = b.value
					});
					return a
				}, {});
				a.filter(B).forEach(function (a) {
					r(a, b[a.sets[0]])
				});
				return a
			}, I = function (a, c) {
				return c.totalOverlap -
					a.totalOverlap
			}, J = function (a) {
				var b = [], d = {};
				a.filter(function (a) {
					return 1 === a.sets.length
				}).forEach(function (a) {
					d[a.sets[0]] = a.circle = {x: Number.MAX_VALUE, y: Number.MAX_VALUE, r: Math.sqrt(a.value / Math.PI)}
				});
				var e = function (a, c) {
					var d = a.circle;
					d.x = c.x;
					d.y = c.y;
					b.push(a)
				};
				H(a);
				var f = a.filter(B).sort(I);
				e(f.shift(), {x: 0, y: 0});
				var h = a.filter(function (a) {
					return 2 === a.sets.length
				});
				f.forEach(function (a) {
					var c = a.circle, f = c.r, g = a.overlapping, k = b.reduce(function (a, e, k) {
						var n = e.circle, l = E(f, n.r, g[e.sets[0]]), z =
							[{x: n.x + l, y: n.y}, {x: n.x - l, y: n.y}, {x: n.x, y: n.y + l}, {x: n.x, y: n.y - l}];
						b.slice(k + 1).forEach(function (a) {
							var b = a.circle;
							a = E(f, b.r, g[a.sets[0]]);
							z = z.concat(w({x: n.x, y: n.y, r: l}, {x: b.x, y: b.y, r: a}))
						});
						z.forEach(function (b) {
							c.x = b.x;
							c.y = b.y;
							var f = F(d, h);
							f < a.loss && (a.loss = f, a.coordinates = b)
						});
						return a
					}, {loss: Number.MAX_VALUE, coordinates: void 0});
					e(a, k.coordinates)
				});
				return d
			}, V = function (a) {
				var b = {};
				0 < a.length && (b = J(a), a.filter(function (a) {
					return !B(a)
				}).forEach(function (a) {
					var c = a.sets;
					a = c.join();
					c = c.map(function (a) {
						return b[a]
					});
					b[a] = t(c)
				}));
				return b
			}, K = function (a) {
				var b = {};
				return p(a) && h(a.value) && -1 < a.value && d(a.sets) && 0 < a.sets.length && !a.sets.some(function (a) {
					var c = !1;
					!b[a] && q(a) ? b[a] = !0 : c = !0;
					return c
				})
			}, L = function (a) {
				a = d(a) ? a : [];
				var b = a.reduce(function (a, b) {
					K(b) && B(b) && 0 < b.value && -1 === a.indexOf(b.sets[0]) && a.push(b.sets[0]);
					return a
				}, []).sort(), g = a.reduce(function (a, c) {
					K(c) && !c.sets.some(function (a) {
						return -1 === b.indexOf(a)
					}) && (a[c.sets.sort().join()] = c);
					return a
				}, {});
				b.reduce(function (a, b, c, d) {
					d.slice(c + 1).forEach(function (c) {
						a.push(b +
							"," + c)
					});
					return a
				}, []).forEach(function (a) {
					if (!g[a]) {
						var b = {sets: a.split(","), value: 0};
						g[a] = b
					}
				});
				return P(g)
			}, W = function (a, c, d) {
				var b = d.bottom - d.top, f = d.right - d.left, b = Math.min(0 < f ? 1 / f * a : 1, 0 < b ? 1 / b * c : 1);
				return {scale: b, centerX: a / 2 - (d.right + d.left) / 2 * b, centerY: c / 2 - (d.top + d.bottom) / 2 * b}
			};
		O("venn", "scatter", {
			borderColor: "#cccccc",
			borderDashStyle: "solid",
			borderWidth: 1,
			brighten: 0,
			clip: !1,
			colorByPoint: !0,
			dataLabels: {
				enabled: !0, formatter: function () {
					return this.point.name
				}
			},
			marker: !1,
			opacity: .75,
			showInLegend: !1,
			states: {
				hover: {opacity: 1, halo: !1, borderColor: "#333333"},
				select: {color: "#cccccc", borderColor: "#000000", animation: !1}
			},
			tooltip: {pointFormat: "{point.name}: {point.value}"}
		}, {
			isCartesian: !1,
			axisTypes: [],
			directTouch: !0,
			pointArrayMap: ["value"],
			translate: function () {
				var a = this.chart;
				this.processedXData = this.xData;
				this.generatePoints();
				var c = L(this.options.data), e = V(c), k = U(c), c = Object.keys(e).filter(function (a) {
						return (a = e[a]) && h(a.r)
					}).reduce(function (a, b) {
						var c = e[b];
						b = c.x - c.r;
						var d = c.x + c.r, f = c.y + c.r, c = c.y -
							c.r;
						if (!h(a.left) || a.left > b) a.left = b;
						if (!h(a.right) || a.right < d) a.right = d;
						if (!h(a.top) || a.top > c) a.top = c;
						if (!h(a.bottom) || a.bottom < f) a.bottom = f;
						return a
					}, {top: 0, bottom: 0, left: 0, right: 0}), a = W(a.plotWidth, a.plotHeight, c), f = a.scale,
					l = a.centerX, m = a.centerY;
				this.points.forEach(function (a) {
					var b = d(a.sets) ? a.sets : [], c = b.join(), g = e[c], h, c = k[c];
					g && (g.r ? h = {x: l + g.x * f, y: m + g.y * f, r: g.r * f} : g.d && (h = {
						d: g.d.reduce(function (a, b) {
							"M" === b[0] ? (b[1] = l + b[1] * f, b[2] = m + b[2] * f) : "A" === b[0] && (b[1] *= f, b[2] *= f, b[6] = l + b[6] * f, b[7] =
								m + b[7] * f);
							return a.concat(b)
						}, []).join(" ")
					}), c ? (c.x = l + c.x * f, c.y = m + c.y * f) : c = {});
					a.shapeArgs = h;
					c && h && (a.plotX = c.x, a.plotY = c.y);
					a.name = a.options.name || b.join("\u2229")
				})
			},
			drawPoints: function () {
				var a = this, c = a.chart, d = a.group, e = c.renderer;
				(a.points || []).forEach(function (b) {
					var f, g = b.shapeArgs;
					c.styledMode || (f = a.pointAttribs(b, b.state));
					b.draw({
						isNew: !b.graphic,
						animatableAttribs: g,
						attribs: f,
						group: d,
						renderer: e,
						shapeType: g && g.d ? "path" : "circle"
					})
				})
			},
			pointAttribs: function (a, c) {
				var b = this.options || {};
				a = N(b,
					{color: a && a.color}, a && a.options || {}, c && b.states[c] || {});
				return {
					fill: u(a.color).setOpacity(a.opacity).brighten(a.brightness).get(),
					stroke: a.borderColor,
					"stroke-width": a.borderWidth,
					dashstyle: a.borderDashStyle
				}
			},
			animate: function (a) {
				if (!a) {
					var b = l.animObject(this.options.animation);
					this.points.forEach(function (a) {
							var c = a.shapeArgs;
							if (a.graphic && c) {
								var d = {}, e = {};
								c.d ? d.opacity = .001 : (d.r = 0, e.r = c.r);
								a.graphic.attr(d).animate(e, b);
								c.d && setTimeout(function () {
									a && a.graphic && a.graphic.animate({opacity: 1})
								}, b.duration)
							}
						},
						this);
					this.animate = null
				}
			},
			utils: {
				addOverlapToSets: H,
				geometry: e,
				geometryCircles: k,
				getDistanceBetweenCirclesByOverlap: E,
				layoutGreedyVenn: J,
				loss: F,
				processVennData: L,
				sortByTotalOverlap: I
			}
		}, {
			draw: a, shouldDraw: function () {
				return !!this.shapeArgs
			}, isValid: function () {
				return h(this.value)
			}
		})
	});
	k(a, "masters/modules/venn.src.js", [], function () {
	})
});
//# sourceMappingURL=venn.js.map