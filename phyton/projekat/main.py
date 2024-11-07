import biblioteka as bb
import datetime 
import matplotlib.pyplot as plt
import numpy as np


def pocetna():
    print("1)napravi novi akaunt clan .")
    print("2)logujse clan")
    print("3)logujse bibliotekar")
    n= input("vas izbor")
    if (n== "1"):
        noviClan()
    elif(n=="2"):
        ClanLog()
    elif(n=="3"):
        BibLog()
    else:
        pocetna()
        
def noviClan():
    ime =input("unesite ime:")
    prezime=input("unesi prezime :")
    email=input("unesi email :")
    passvord=input("unesi password ")
    cl= bb.clan(0, ime, prezime, email, passvord)
    us= bb.napraviClana(cl)
    if(us):
        print("uspesno ste dodali clana")
        
    else:
        print("niste uspeli probajte ponovo")
        noviClan()
    pocetna() 
   
def ClanLog():
    
    email=input("unesi email:")
    password=input("unesi password :")
    c=bb.LogClan(email, password)
    if c== None:
        print("pogresno email ili lozonka")
        ClanLog()
    else:
        clanRadi(c)
        

def clanRadi(c):
    print("clanRadi------------------")
    print("1)preglen dostupnih knjiga.")
    print("2)iznajmi knjigi")
    print("3)vidi svoje pozajmice")
    print("4)dase vratite na pocetni")
    
    n= input("vas izbor")
    
    
    if (n== "1"):#pregled dostupnih knjiga
        knjige=bb.SveKnjige()
        for i in knjige :
            if bb.DostupnaKnjiga(i):
                print("id :{:<2}| naziv{:<20} ".format(i.idk,i.naziv))
    elif(n=="2"):
        idknji=eval(input("unesi id knjige za iznajmljivanje"))
        bool=bb.iznajmiKnjigu(idknji, c)
        if bool:
            print("uspesno iznajmili knjigu")
            clanRadi(c)
        else:
            print("nema na stanju")
            clanRadi(c)
    elif(n=="3"):#pregleb iznajmljenih kj
        
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
       
       clanRadi(c)
    elif(n=="4"):#vracanje na pocetnu str
        pocetna()
    else:#ako pogresimo
        clanRadi(c)
        
def BibLog():
    
    email=input("unesi email:")
    password=input("unesi password :")
    b=bb.LogBib(email, password)
    if b== None:
        print("pogresno email ili lozonka")
        BibLog()
    else:
        biblRadi(b)
    
def biblRadi(b):
    
    print("1)dodaj novi knjigu")
    print("2)dodaj broj primeraka")
    print("3)proveri pozajmice")
    print("4)potvrdi vraca je")
    print("5)statistika pozajmica")
    print("6)vratise na pocetnu")
    n=input("vas izbor :")
   
    if (n== "1"):
        
        naziv=input("unesi naziv :")
        br=eval(input("unesi broj primeraka :"))
        bb.DodajKnj(naziv, br)
        biblRadi(b)
        
    elif(n=="2"):
        
        print("2")
        idk=eval(input("unesi id knjige za promenu  :"))
        brDod=eval(input("unesi broj dodadih primeraka  :"))
       
        bb.DodajPrimerk(idk, brDod)
        biblRadi(b)
        
    elif(n=="3"):
        
        sve=input("zelite samo one koje nisu vracene sa upozorenjem ya kasnjenje da/ne :")
        while sve !="da" and sve !="ne":
            sve=input("zelite samo one koje nisu vracene sa upozorenjem ya kasnjenje da/ne :")
        if sve=="ne":
            poz=bb.SvePozajmice()
            for p in poz:
                p.ispisi()
            biblRadi(b)
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
            biblRadi(b)
            
    elif(n=="4"):
        
        idk=eval(input("unesi id knjige koju vraca :"))
        idc=eval(input("unesi id clana koji vraca  :"))
        poz=bb.NadiPozajm(idk, idc)
        for p in poz:
            p.ispisi()
            
        idp=eval(input("unesi id pozajmice za potvrdu  :"))
        bb.VratiKnj(idp)
        biblRadi(b)
    elif(n=="5"):
        Statistika()
        
    elif(n=="6"):
        
        pocetna()
        
    else:
        
        biblRadi(b)

def Statistika():
     
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
        
            
    
    
        
