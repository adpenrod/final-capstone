<template>
    <section class="about-container">
      <h1>About Us</h1>
      <div class="card-container">
        
        <div class="card">
          <div class="card-content">
  <a href="https://www.linkedin.com/in/alan-penrod/" target="_blank">
  <img src="../assets/logos/alanbig.png" alt="Photo of Alan Penrod" />
  </a>
            <h3>Alan Penrod</h3>
            <p>TE Graduate | Software Developer</p>
          </div>
        </div>

        <div class="card">
          <div class="card-content">
  <a href="https://www.linkedin.com/in/andrewwasham10" target="_blank">
  <img src="../assets/logos/andrew.png" alt="Photo of Andrew Washam" />
  </a>
            <h3>Andrew Washam</h3>
            <p>TE Graduate | Software Developer</p>
          </div>
        </div>

        <div class="card">
             <div class="card-content">
  <a href="https://linkedin.com/in/aniqueclements" target="_blank">
  <img src="../assets/logos/anique.png" alt="Photo of Anique Clements" />
  </a>
            <h3>Anique Clements</h3>
            <p>TE Graduate | Software Developer</p>
          </div>
        </div>

        <div class="card">
            <div class="card-content">
<a href="https://www.linkedin.com/in/caseyjamisondedev" target="_blank">
  <img src="../assets/logos/casey.png" alt="Photo of Casey Jamison" />
  </a>
            <h3>Casey Jamison</h3>
            <p>TE Graduate | Software Developer</p>

        </div> 
        </div>


        <div class="card">
          <div class="card-content">
  <a href="https://www.linkedin.com/in/davidgshipley/" target="_blank">
  <img src="../assets/logos/david.png" alt="Photo of David Shipley" />
  </a>
            <h3>David Shipley</h3>
            <p>TE Graduate | Software Developer</p>
          </div>   
        </div>


        <div class="card">
           <div class="card-content">
  <a href="https://www.linkedin.com/in/ralinscott/" target="_blank">
  <img src="../assets/logos/ralin.png" alt="Photo of Ralin Scott" />
  </a>
            <h3>Ralin Scott</h3>
            <p>TE Graduate | Software Developer</p>
          </div>
        </div>

        <div class="card">
          <div class="card-content">
            <img src="../assets/logos/liberty.png" alt="Photo of Liberty Bellman" />
            <h3>Liberty Bellman</h3>
            <p>Mascot</p>
          </div>
        </div>




    </div>
    
    </section>
  </template>
  
  <style scoped>
  .about-container {
    padding: 20px;
    text-align: center;
  }
  
  .about-container h1 {
    font-size: 2rem;
    margin-bottom: 20px;
    color: #6e3f13;
  }
  
  .card-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 16px;
    padding: 16px;
  }
  
  .card {
    background-color: var(--neutral-color);
    border: 1px solid #f7cd75;
    border-radius: 8px;
    overflow: hidden;
    transition: box-shadow 0.3s ease;
  }
  
  .card-content {
    padding: 16px;
    text-align: center;
  }
  
  .card img {
    width: 100%;
    height: 370px;
    object-fit: cover;
    border-bottom: 1px solid #f7cd75;
    border-radius: 4px;
  }
  
  .card h3 {
    margin: 12px 0;
    color: var(--text-color);
  }
  
  .card p {
    color: var(--text-color);
  }
  
  .card:hover {
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  }
  </style>
  
  
  