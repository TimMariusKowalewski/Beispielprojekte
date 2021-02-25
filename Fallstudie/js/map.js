function getCurrentLocation(callback) {
    if (!navigator.geolocation) return;
    navigator.geolocation.getCurrentPosition(function (position) {
        callback(position);
    });
}

getCurrentLocation(function (position) {
    var map = new ol.Map({
        target: 'map',
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            })
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude]),
            zoom: 11
        })
    });
    var countryStyle = new ol.style.Style({
        fill: new ol.style.Fill({
            color: [128, 128, 128, 1]
        }),
        stroke: new ol.style.Stroke({
            color: [128, 128, 128, 0.5],
            width: 2,
            lineCap: 'round'
        })
    });
    var layer = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: [
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude - 0.01, position.coords.latitude - 0.01]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude - 0.03, position.coords.latitude - 0.03]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude + 0.03, position.coords.latitude + 0.03]))
                }),
            ]
        })
    });
    map.addLayer(layer);
    var container = document.getElementById('popup');
    var content = document.getElementById('popup-content');
    var closer = document.getElementById('popup-closer');

    var overlay = new ol.Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlay);

    closer.onclick = function () {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
    };

    map.on('singleclick', function (event) {
        if (map.hasFeatureAtPixel(event.pixel) === true) {
            var coordinate = event.coordinate;

            content.innerHTML = '<b>Hello world!</b><br />I am a popup.';
            overlay.setPosition(coordinate);
        } else {
            overlay.setPosition(undefined);
            closer.blur();
        }
    });

    // map2
    var map2 = new ol.Map({
        target: 'map2',
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            })
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude]),
            zoom: 11
        })
    });
    var countryStyle = new ol.style.Style({
        fill: new ol.style.Fill({
            color: [128, 128, 128, 1]
        }),
        stroke: new ol.style.Stroke({
            color: [128, 128, 128, 0.5],
            width: 2,
            lineCap: 'round'
        })
    });
    var layer = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: [
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude - 0.01, position.coords.latitude - 0.01]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude - 0.03, position.coords.latitude - 0.03]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude + 0.03, position.coords.latitude + 0.03]))
                }),
            ]
        })
    });
    map.addLayer(layer);
    var container = document.getElementById('popup2');
    var content = document.getElementById('popup-content2');
    var closer = document.getElementById('popup-closer2');

    var overlay = new ol.Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlay);

    closer.onclick = function () {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
    };

    map.on('singleclick', function (event) {
        if (map.hasFeatureAtPixel(event.pixel) === true) {
            var coordinate = event.coordinate;

            content.innerHTML = '<b>Hello world!</b><br />I am a popup.';
            overlay.setPosition(coordinate);
        } else {
            overlay.setPosition(undefined);
            closer.blur();
        }
    });

    // map3
    var map3 = new ol.Map({
        target: 'map3',
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            })
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude]),
            zoom: 11
        })
    });
    var countryStyle = new ol.style.Style({
        fill: new ol.style.Fill({
            color: [128, 128, 128, 1]
        }),
        stroke: new ol.style.Stroke({
            color: [128, 128, 128, 0.5],
            width: 2,
            lineCap: 'round'
        })
    });
    var layer = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: [
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude - 0.01, position.coords.latitude - 0.01]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude - 0.03, position.coords.latitude - 0.03]))
                }),
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([position.coords.longitude + 0.03, position.coords.latitude + 0.03]))
                }),
            ]
        })
    });
    map.addLayer(layer);
    var container = document.getElementById('popup3');
    var content = document.getElementById('popup-content3');
    var closer = document.getElementById('popup-closer3');

    var overlay = new ol.Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlay);

    closer.onclick = function () {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
    };

    map.on('singleclick', function (event) {
        if (map.hasFeatureAtPixel(event.pixel) === true) {
            var coordinate = event.coordinate;

            content.innerHTML = '<b>Hello world!</b><br />I am a popup.';
            overlay.setPosition(coordinate);
        } else {
            overlay.setPosition(undefined);
            closer.blur();
        }
    });
});