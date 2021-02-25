from scipy.stats import norm
import random
import time
import matplotlib.pyplot as plt
import os
import glob

peopleDictionary = []


class Virus():
    def chance_of_infection():
        # gauss'sche Normalverteilung um 5 * x in Prozent
        x = 2
        return int((norm.rvs(size=1, loc=0.5, scale=0.15)[0] * 10).round(0) * x)


# simulation of a single person
class Person():
    def __init__(self, startingImmunity):
        if random.randint(0, 100) < startingImmunity:
            self.immunity = True
        else:
            self.immunity = False
        self.contagiousness = 0
        self.mask = False
        self.contagiousDays = 0
        # use gaussian distribution for number of contacts; average is 15 contacts
        self.friends = int(
            (norm.rvs(size=1, loc=0.5, scale=0.15)[0] * 30).round(0))

    def wearMask(self, eff):
        self.contagiousness /= eff


def initiateSim():
    os.system('cls')
    numPeople = int(input("Population: "))
    startingImmunity = int(
        input("Percentage of people with natural immunity: "))
    startingInfecters = int(
        input("How many people will be infectious at t=0: "))
    for x in range(0, numPeople):
        peopleDictionary.append(Person(startingImmunity))
    for x in range(0, startingInfecters):
        peopleDictionary[random.randint(0, len(
            peopleDictionary) - 1)].contagiousness = Virus.chance_of_infection()
    daysContagious = int(input("Dauer der Infektiosität in Tagen: "))
    lockdownDay = int(input("Lockdown startet an Tag: "))
    lockdown_eff = int(
        input("Kontaktreduzierung durch den Lockdown in ganzen Prozent: "))
    maskDay = int(input("Masken werden benutzt an Tag: "))
    mask_eff = int(input("Effektivität von Masken in ganzen Prozent: "))
    mask_num = int(
        input("Anteil der Menschen, die Masken tragen in ganzen Przent:"))
    simdays = int(input("Dauer der Simulation in Tagen: "))
    idx = input("Index der Simulation: ")
    return daysContagious, lockdownDay, maskDay, lockdown_eff, mask_eff, simdays, mask_num, idx, numPeople, startingInfecters, startingImmunity


def runDay(daysContagious, lockdown, lockdown_eff):
    # this section simulates the spread, so it only operates on contagious people, thus:
    for person in [person for person in peopleDictionary if
                   person.contagiousness > 0 and person.friends > 0]:
        peopleCouldMeetToday = int(person.friends / 2)
        if peopleCouldMeetToday > 0:
            peopleMetToday = random.randint(0, peopleCouldMeetToday)
        else:
            peopleMetToday = 0

        if lockdown == True:
            peopleMetToday = int(peopleMetToday / lockdown_eff)

        for x in range(0, peopleMetToday):
            friendInQuestion = peopleDictionary[
                random.randint(0, len(peopleDictionary) - 1)]
            if random.randint(0,
                              100) < person.contagiousness and friendInQuestion.contagiousness == 0 and friendInQuestion.immunity == False:
                friendInQuestion.contagiousness = Virus.chance_of_infection()
                print(peopleDictionary.index(person), " >>> ",
                      peopleDictionary.index(friendInQuestion))
    for person in [person for person in peopleDictionary if
                   person.contagiousness > 0]:
        person.contagiousDays += 1
        if person.contagiousDays > daysContagious:
            person.immunity = True
            person.contagiousness = 0
            print("|||", peopleDictionary.index(person), " |||")
            lockdown = False


def plot_graph():
    fig, ax = plt.subplots()
    ax.set(xlabel="Tage", ylabel="aktive Infektionen", title="pandemic.py")
    pattern = r"*.pplot"
    files = glob.glob(pattern)
    print(files)
    for f in files:
        x = open(f, "r")
        infected = x.read().splitlines()
        p = infected[0]
        infected_float = [float(line) for line in infected[1:len(infected) - 1]]
        time = range(0, len(infected_float))
        ax.plot(time, infected_float, label=p)
    ax.legend(loc="upper left", title="Params", frameon=False)
    plt.show()


daysContagious, lockdownDay, maskDay, lockdown_eff, mask_eff, simdays, mask_num, idx, numPeople, startingInfecters, startingImmunity = initiateSim()
saveFile = open("p_" + str(idx) + ".pplot", "w")
saveFile.write(
    "Pop:" + str(numPeople) + " Imm." + str(startingImmunity) + " T0:" + str(
        startingInfecters) + " Dauer:" + str(daysContagious) + " LD:" + str(
        lockdownDay) + " LD_eff:" + str(lockdown_eff) + " MNS:" + str(
        maskDay) + " MNS_eff:" + str(mask_eff) + " MNS%:" + str(
        mask_num) + "\n")

lockdown = False
for x in range(0, simdays):
    if x == lockdownDay:
        lockdown = True

    if x == maskDay:
        for person in random.choices(peopleDictionary, k=int(
                len(peopleDictionary) * mask_num / 100)):
            person.wearMask(mask_eff)

    print("DAY ", x)
    runDay(daysContagious, lockdown, lockdown_eff)
    write = str(len([person for person in peopleDictionary if
                     person.contagiousness > 0])) + "\n"
    saveFile.write(write)
    print(len(
        [person for person in peopleDictionary if person.contagiousness > 0]),
        " people are contagious on this day.")
saveFile.close()
plot_graph()
