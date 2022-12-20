import {
    IonButton,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
    useIonRouter,
    UseIonRouterResult,
    useIonViewDidEnter,
  } from "@ionic/react";
  import axios from "axios";
  import { useEffect, useState } from "react";
import Avionc from "../components/Avionc";
import { Avion } from "../modele/Avion";
  import Token from "../VehiculeManage/Token";
  import Vehicule from "../VehiculeManage/Vehicule";
  import "./Home.css";

  function voir (value: String){
    localStorage.setItem("idavion",""+value);
  }
  function appendData(data : Avion[],router:UseIonRouterResult) {
    const tbody=document.getElementById("tbody");
    data.forEach((avion : Avion) => {
      let tr=document.createElement("tr");
      let td1=document.createElement("td");
      let td2=document.createElement("td");
      let td3=document.createElement("td");
      td1.className="ion-text-center";
      td2.className="ion-text-center";
      td3.className="ion-text-center";
      td1.append(""+avion.idAvion);
      td2.append(""+avion.modele);
      let link=document.createElement("a");
      link.href="/fiche/"+avion.idAvion;
      link.append("voir");
      td3.appendChild(link);
      tr.appendChild(td1);
      tr.appendChild(td2);
      tr.appendChild(td3);
      tbody!.appendChild(tr);
    });
  }  
  
  
  const Home: React.FC = () => {
    const router = useIonRouter();
    //------------------------------------------ws-------------------------------//
    const   sendingAPI=  () =>  {
        let data : Avion[];
        const api = axios.create({
          baseURL: "http://192.168.43.57:4091",
        });
       
         api
          .get("/avions")
          .then((res) => {
            data = res.data.data; 
            appendData(data,router);
          })
          .catch((err) => { throw err});
      }
    // -----------------------------------------------WS-------------------------//
    let cpt =0;
    useIonViewDidEnter(() => {
        if(cpt===0){
            sendingAPI();
            cpt++;
        }
      }
    );
  
    return (
      <IonPage>
        <table className="ion-margin,ion-border">
          <thead>
            <th>Id Avion</th>
            <th>Modele</th>
            <th>Link </th>
            <th>Image</th>
          </thead>
          <tbody id="tbody">
          </tbody>
        </table>
      </IonPage>
);
  };
  export default Home;
  