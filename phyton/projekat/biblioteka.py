import mysql.connector
import datetime

db= mysql.connector.connect(
    host ="localhost",
    user="root",
    passwd="root",
    database="biblioteka"
    )
pokaz=db.cursor()


class clan:
    def __init__(self,idc, ime, prezime , email , password):
        self.idc=idc
        self.ime=ime
        self.prezime=prezime
        self.email=email
        self.password=password
        
    def ispisi(self):
        print("id:{:<2} ime:{:<10} | prezime :{:<10}".format(self.idc,self.ime, self.prezime))
        
class Bibliotekar:
    def __init__(self,idbib, ime, prezime , email , password):
        self.idbib=idbib
        self.ime=ime
        self.prezime=prezime
        self.email=email
        self.password=password
        
    def ispisi(self):
        print("id:{:<2} ime:{:<20} | prezime{:<20}".format(self.idbib,self.ime, self.prezime))


class knjiga:
    def __init__(self,idk, naziv,brprimeraka):
        self.idk=idk
        self.naziv=naziv
        self.brprimeraka=brprimeraka
        
    def ispisi(self):
        print("id :{:<2} | naziv:{:<15} | broj primeraka {:<10}".format(self.idk , self.naziv, self.brprimeraka))
        
class pozajmica:
    def __init__(self,idp, datum, vracena , idk , idc):
        self.idp = idp
        self.datum = datum
        self.vracena=vracena
        self.idk = idk
        self.idc = idc
        
    def ispisi(self):
        
        print("id:{:<2} datum: {} | vracena {:<1} |knjiga {:<2}|clan {:<2}".format( self.idp , self.datum , self.vracena ,self.idk,self.idc ))
        
#prijavljivanje-----------------------------------------------------------------------

def napraviClana(clanO):
    pokaz.execute("select * from clan where email=%s or password= %s",(clanO.email ,clanO.password))
    if( pokaz.fetchone() !=None):
        return False
    pokaz.execute("INSERT INTO clan (ime , prezime , email , password) Values(%s,%s,%s,%s)",
                  (clanO.ime , clanO.prezime , clanO.email ,clanO.password))
    db.commit()
    return True




#Logovanje Clan-----------------------------------------------------------------------------

def LogClan(email , password):
  pokaz.execute("select * from clan where email=%s and password= %s",(email ,password)) 
  x=pokaz.fetchone()
  if(x == None):
      return None
  o = clan(x[0] ,x[1],x[2],x[3],x[4])
  return o


def NadiClana(idc):
    pokaz.execute("select * from clan where idc=%s",(idc,))
    x=pokaz.fetchone()
    if(x == None):
        return None
    o = clan(x[0] ,x[1],x[2],x[3],x[4])
    return o

def NadiKnjigu(idk):
    pokaz.execute("select * from knjiga where idk= %s",(idk,))
    x=pokaz.fetchone()
    if(x == None):
        return None
    k=knjiga(x[0] ,x[1],x[2])
    return k

def NadiPozajm(idk ,idc):
    pokaz.execute("select * from pozajmica where idknjige= %s and idclana =%s",(idk,idc))
    x=pokaz.fetchall()
    if(x == None):
        return None
    po=[]
    for row in x:
        po.append(pozajmica(row[0], row[1], row[2],row[3], row[4]))
    return po
    


#Logovanje BIBLIOTEKAR----------------------------------------------------------------------

def LogBib(email , password):
  pokaz.execute("select * from bibliotekar where email=%s or password= %s",(email ,password)) 
  x=pokaz.fetchone()
  if( x ==None):
      return None
  o = Bibliotekar(x[0] ,x[1],x[2],x[3],x[4])
  return o




#dodaj knjigu------------------------------------------------------------------------------
def DodajKnj(naziv , brprimeraka):
    pokaz.execute("INSERT INTO knjiga (naziv ,brprimeraka) Values(%s,%s)",
                  (naziv ,brprimeraka))
    db.commit()


#Delete knjiga--------------------------------------------------------------------
def UkloniKnj(idk):
    pokaz.execute("delete from knjiga where IDk = %s",
                  (idk,))
    db.commit()


   
#Delete clana--------------------------------------------------------------------------
def UkloniClana(idc):
    pokaz.execute("delete from clan where IDk = %s",
                  (idc,))
    db.commit()



#pregled kniga---------------------------------------------------
def SveKnjige():
    knjige = []
    pokaz.execute("select * from knjiga")
    for row in pokaz.fetchall():
        k= knjiga(row[0], row[1], row[2])
        knjige.append(k)
    return knjige



def DostupnaKnjiga(k):
    pokaz.execute("select * from pozajmica where idknjige = %s and vracena = false",(k.idk,))
    n=0
    for i in pokaz:
        n=n+1
    if(n < k.brprimeraka):
        return True
    return False
    
    

#pregled clana---------------------------------------------------
def SviClanovi():
    knjige = []
    pokaz.execute("select * from clan")
    for row in pokaz.fetchall():
        k= clan(row[0], row[1], row[2], row[3],row[4])
        knjige.append(k)
    return knjige

#dobavi 1 knjigu na osnovu idk---------------------------------------------------

def DobaviKnjigu(idk):
    pokaz.execute("select * from knjiga where idk=%s",(idk,))
    row=pokaz.fetchone()
    return knjiga(row[0], row[1], row[2])

#Iznajmi knjigu---------------------------------------------------
def iznajmiKnjigu(idk , c):
    dos=DostupnaKnjiga(DobaviKnjigu(idk))
    
    if dos:    
        pokaz.execute("INSERT INTO pozajmica (datumUzimanja ,vracena,idknjige,idclana) Values(%s,%s,%s,%s)",
                      (datetime.date.today() , False , idk , c.idc))
        db.commit()
        return True
    else:
        return False

        
#Sve Pozajmice---------------------------------------------------------------        
def SvePozajmice():
    poz=[]
    pokaz.execute("select * from pozajmica ORDER BY datumUzimanja DESC")
    for row in pokaz.fetchall():
        
        p=pozajmica(row[0], row[1].date(), row[2], row[3], row[4])
        poz.append(p)
    return poz

def SvePozajmiceNe():
    poz=[]
    pokaz.execute("select * from pozajmica where vracena =false ORDER BY datumUzimanja DESC")
    for row in pokaz.fetchall():
        
        p=pozajmica(row[0], row[1].date(), row[2], row[3], row[4])
        poz.append(p)
    return poz



def SvePozajmiceZa(idc):
    poz=[]
    pokaz.execute("select * from pozajmica where idclana = %s ",(idc,))
    for row in pokaz.fetchall():
        p=pozajmica(row[0], row[1].date(), row[2], row[3], row[4])
        poz.append(p)
    return poz



#vracanje knjige--------------------------------------------------------------
def VratiKnj(idp):
    pokaz.execute("update pozajmica set vracena = true where idp = %s",(idp,))
    db.commit()



    

def Ukoriscenju(idk):
    pokaz.execute("select * from pozajmica where idknjige = %s",(idk,))
    n=0
    for i in pokaz:
        n=n+1
    return n
    
def DodajPrimerk(idk,brD):
    pokaz.execute("update knjiga set brprimeraka=brprimeraka +%s where idk = %s",(brD,idk))
    db.commit()

#statistike --------------------------------------------------------
def dostaviStats(imena,primer,poza):
    knjige=SveKnjige()
    
    for k in knjige:
        imena.append(k.naziv)
        primer.append(k.brprimeraka)
        pokaz.execute("select COUNT(*) from pozajmica where idknjige = %s and datumUzimanja > %s",(k.idk,datetime.date.today()-datetime.timedelta(days=90)))
        br= pokaz.fetchone()[0]
        poza.append(br/3)
        
