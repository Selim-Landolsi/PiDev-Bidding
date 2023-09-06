<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Repository\ProduitRepository;
use App\Repository\ProduitManager;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
    #[Route('/home', name: 'app_home')]
    public function index(): Response
    {
        return $this->render('home/index.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }

    #[Route('/back', name: 'app_home2')]
    public function index2(): Response
    {
        return $this->render('home/index2.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    
    #[Route('/prod', name: 'prod')]
    public function prod(ProduitRepository $produitR, Request $request): Response
    {   $id = $request->query->get('id');
        $produit = $produitR->find($id);
        return $this->render('home/prod.html.twig', [
            'controller_name' => 'HomeController',
            'produit' => $produit ,
        ]);
    }
}
