package com.example.MediGo.model.Local



data class Data(
    val doctors: List<Doctor>,
    val health_packages: List<HealthPackage>,
    val service_categories: List<ServiceCategory>,
    val specialists: List<Specialists>
)