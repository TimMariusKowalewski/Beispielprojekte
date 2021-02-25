import tracy.config as config

# Replace libraries by fake ones
devel = True  # !!!Immer nur False comitten!!!
if devel:
    import sys
    import fake_rpi
    from fake_rpi import toggle_print

    sys.modules['RPi'] = fake_rpi.RPi     # Fake RPi
    sys.modules['RPi.GPIO'] = fake_rpi.RPi.GPIO  # Fake GPIO
    sys.modules['smbus'] = fake_rpi.smbus  # Fake smbus (I2C)
    toggle_print(False)  # True für debug output
# Replace libraries by fake ones

# Startet die Hauptschleife des Programms
config.TR.init()
if __debug__:
    config.TR.logger.debug('starting main loop...')
config.TR.controller.run()
