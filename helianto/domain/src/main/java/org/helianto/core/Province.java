package org.helianto.core;
// Generated Apr 24, 2006 9:34:30 PM by Hibernate Tools 3.1.0.beta4



/**
 * 			
 * <p>
 * Provinces.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-entity1.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 		
 */

public class Province  implements java.io.Serializable {


    // Fields    

     private int id;
     private Home home;
     private String code;
     private String provinceName;
     private String country;


    // Constructors

    /** default constructor */
    public Province() {
    }

	/** minimal constructor */
    public Province(Home home, String code) {
        this.home = home;
        this.code = code;
    }
    
    /** full constructor */
    public Province(Home home, String code, String provinceName, String country) {
        this.home = home;
        this.code = code;
        this.provinceName = provinceName;
        this.country = country;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Home getHome() {
        return this.home;
    }
    
    public void setHome(Home home) {
        this.home = home;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Province) ) return false;
		 Province castOther = ( Province ) other; 
         
		 return ( (this.getHome()==castOther.getHome()) || ( this.getHome()!=null && castOther.getHome()!=null && this.getHome().equals(castOther.getHome()) ) )
 && ( (this.getCode()==castOther.getCode()) || ( this.getCode()!=null && castOther.getCode()!=null && this.getCode().equals(castOther.getCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getHome() == null ? 0 : this.getHome().hashCode() );
         result = 37 * result + ( getCode() == null ? 0 : this.getCode().hashCode() );
         
         
         return result;
   }   





}
