import biblioteka as bb
import datetime 
import matplotlib.pyplot as plt
import numpy as np


def pocetna():
    print("1)napravi novi akaunt clan .")
    print("2)logujse clan")
    print("3)logujse bibliotekar")
    n= "2" #input("vas izbor")
    if (n== "1"):
        noviClan()
    elif(n=="2"):
        ClanLog()
    elif(n=="3"):
        BibLog()
    else:
        pocetna()
        
def noviClan():
    ime ="petar" #input("unesite ime:")
    prezime="petrovic"#input("unesi prezime :")
    email="pera1@gmail.com"#input("unesi email :")
    passvord="perr1"#input("unesi password ")
    cl= bb.clan(0, ime, prezime, email, passvord)
    us= bb.napraviClana(cl)
    if(us):
        print("uspesno ste dodali clana")
        
        
    else:
        print("niste uspeli probajte ponovo")
        #noviClan()
   #pocetna() 
   
def ClanLog():
    print("clanlog------------------")
    email="pera1@gmail.com"#input("unesi email:")
    password="perr1"#input("unesi password :")
    c=bb.LogClan(email, password)
    if c== None:
        print("pogresno email ili lozonka")
    else:
        clanRadi(c)
        

def clanRadi(c):
    print("clanRadi------------------")
    print("1)preglen dostupnih knjiga.")
    print("2)iznajmi knjigi")
    print("3)vidi svoje pozajmice")
    print("4)dase vratite na pocetni")
    
    n= "3"#input("vas izbor")------------------------------------
    
    
    if (n== "1"):#pregled dostupnih knjiga
        knjige=bb.SveKnjige()
        for i in knjige :
            if bb.DostupnaKnjiga(i):
                print("id :{:<2}| naziv{:<20} ".format(i.idk,i.naziv))
    elif(n=="2"):
        idknji=5#eval(input("unesi id knjige za iznajmljivanje"))--------------
        bool=bb.iznajmiKnjigu(idknji, c)
        if bool:
            print("uspesno iznajmili knjigu")
            #clanRadi(c)
        else:
            print("nema na stanju")
            #clanRadi(c)
    elif(n=="3"):#preglenb iznajmljenih kj
        
       poz= bb.SvePozajmiceZa(c.idc)
       print("iznajmnjene knjige su:")
       for i in poz:
           por="datup uzimanja {}".format(i.datum)
           knjig=bb.DobaviKnjigu(i.idk)
           por=por+"|naziv knjige:{:<15}".format(knjig.naziv)
           if i.vracena:
               por =por +"|varcena: Da"
           else:
               por =por +"|varcena: ne"
           print(por)  
       
       #clanRadi(c)
    elif(n=="4"):#vracanje na pocetnu str
        pocetna()
    else:#ako pogresimo
        clanRadi(c)
        
def BibLog():
    print("Bililog------------------\n")
    email="kon@gamil.com"#input("unesi email:")
    password="kon"#input("unesi password :")
    b=bb.LogBib(email, password)
    if b== None:
        print("pogresno email ili lozonka")
    else:
        biblRadi(b)
    
def biblRadi(b):
    print("bibl radi ---------------------------------------\n")
    print("1)dodaj novi knjigu")
    print("2)dodaj broj primeraka")
    print("3)proveri pozajmice")
    print("4)potvrdi vraca je")
    print("5)statistika pozajmica")
    print("6)vratise na pocetnu")
    n="5"#input("vas izbor :")---------------------------------------------------------------------
   
    if (n== "1"):
        print("1")
        naziv="robinzon kruso"#input("unesi naziv :")
        br=6 #eval(input("unesi broj primeraka :")
        bb.DodajKnj(naziv, br)
        #biblRadi(b)
    elif(n=="2"):
        print("2")
        idk=8#eval(input("unesi id knjige za promenu  :")
        brDod=5#eval(input("unesi broj dodadih primeraka  :")
       
        bb.DodajPrimerk(idk, brDod)
        #biblRadi(b)
    elif(n=="3"):
        print("3")
        
        sve="da" #input("zelite samo one koje nisu vracene sa upozorenjem ya kasnjenje da/ne :")------------------------------------
        if sve=="ne":
            poz=bb.SvePozajmice()
            for p in poz:
                p.ispisi()
        elif sve=="da":
            
            
            poz=bb.SvePozajmiceNe()
            listaZaUp={}
            for p in poz:
                
                rok = datetime.date.today()-datetime.timedelta(days=30)#rok za vracanje je 30 dana
                if p.datum < rok:
                    print("upozorenje :" )
                    clan=bb.NadiClana(p.idc)
                    knjig=bb.NadiKnjigu(p.idk)
                    listaZaUp[clan.email]=knjig.naziv
                p.ispisi()
                
            print(listaZaUp)
            #biblRadi(b)
    elif(n=="4"):
        print("4")
        idk=5#eval(input("unesi id knjige koju vraca :")
        idc=1#eval(input("unesi id clana koji vraca  :")
        poz=bb.NadiPozajm(idk, idc)
        for p in poz:
            p.ispisi()
            
        idp=2#eval(input("unesi id pozajmice za potvrdu  :")
        bb.VratiKnj(idp)
        #biblRadi(b)
    elif(n=="5"):
        
        print("5")
        #biblRadi(b)
        Statistika()
    elif(n=="6"):
        print("6")
        pocetna()
    else:
        biblRadi(b)

def Statistika():
    print("stats")  
    imena = []
    primer = []
    poza = []
    bb.dostaviStats(imena,primer, poza)
  
    x = np.arange(len(imena)) # pozicija labela
    width = 0.35 # širina stubića
    fig, ax = plt.subplots()
    
    ax.bar(x - width/2, primer, width, label='broj primeraka')
    ax.bar(x + width/2, poza, width, label='prosek pozajmica u 3m')
    

    ax.set_title('statistika stana i pozajmica')
    ax.set_xticks(x)
    ax.set_xticklabels(imena)
    ax.legend()
    plt.show()
    

pocetna()
        
            
    
    
        
