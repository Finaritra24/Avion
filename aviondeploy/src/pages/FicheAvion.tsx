import {
    IonButton,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
    UseIonRouterResult,
    useIonViewWillEnter,
  } from "@ionic/react";
  import axios from "axios";
  import { useEffect, useState } from "react";
  import Token from "../VehiculeManage/Token";
  import Vehicule from "../VehiculeManage/Vehicule";
  

  // function sendingAPI() { 
  //  const token :Token = {token : window.localStorage.getItem("token")!};
  //  const api=axios.create(
  //   {
  //     baseURL:"http://172.16.0.174:4091", 
  //   }
  // );
  //   api.post("/vehiculees", token ).then((res)=>{
  //     const data  = res.data.data;
  //       alert(JSON.stringify(data));
  //   }).catch(err=>(alert(err)));
  // }
  

  const FicheAvion : React.FC = () => {
    const params = new URLSearchParams(window.location.pathname);
    console.log(params)
    return <p>{params}</p>;
  };
  export default FicheAvion;
  