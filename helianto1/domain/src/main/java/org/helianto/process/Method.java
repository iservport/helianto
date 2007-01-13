package org.helianto.process;
// Generated 13/01/2007 07:27:02 by Hibernate Tools 3.2.0.beta8


import org.helianto.core.Entity;

/**
 * 				
 * <p>
 * A method is required to complete an association between
 * a characteristic and a specification.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Method  implements java.io.Serializable {

    // Fields    

     private long id;
     private Entity entity;
     private Resource resource;
     private double sampleSize;
     private String sampleFrequency;
     private String techinique;
     private String control;

     // Constructors

    /** default constructor */
    public Method() {
    }

	/** minimal constructor */
    public Method(double sampleSize) {
        this.sampleSize = sampleSize;
    }
    /** full constructor */
    public Method(Entity entity, Resource resource, double sampleSize, String sampleFrequency, String techinique, String control) {
       this.entity = entity;
       this.resource = resource;
       this.sampleSize = sampleSize;
       this.sampleFrequency = sampleFrequency;
       this.techinique = techinique;
       this.control = control;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public Resource getResource() {
        return this.resource;
    }
    
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public double getSampleSize() {
        return this.sampleSize;
    }
    
    public void setSampleSize(double sampleSize) {
        this.sampleSize = sampleSize;
    }
    public String getSampleFrequency() {
        return this.sampleFrequency;
    }
    
    public void setSampleFrequency(String sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }
    public String getTechinique() {
        return this.techinique;
    }
    
    public void setTechinique(String techinique) {
        this.techinique = techinique;
    }
    public String getControl() {
        return this.control;
    }
    
    public void setControl(String control) {
        this.control = control;
    }




}


