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
use App\Form\ProdType;

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
    #[Route('/prod/{id}', name: 'app_prod_show', methods: ['GET'])]
    public function show(Produit $prod): Response
    {
        return $this->render('home/show.html.twig', [
            'produit' => $prod,
            'message'=>''
        ]);
    }

    #[Route('/prod/{id}/edit', name: 'app_prod_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Produit $prod, ProduitRepository $ProdRepository): Response
    {
        $form = $this->createForm(ProdType::class, $prod);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $ProdRepository->save($prod, true);

            return $this->redirectToRoute('app_prod_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('home/edit.html.twig', [
            'produit' => $prod,
            'form' => $form,
        ]);
    }
    #[Route('/prods', name: 'app_prod_index', methods: ['GET'])]
    public function indexProd(ProduitRepository $ProdRepository): Response
    {
        return $this->render('home/prods.html.twig', [
            'produits' => $ProdRepository->findAll(),
        ]);
    }
    #[Route('/back/prods', name: 'app_prod_back_index', methods: ['GET'])]
    public function indexProdBack(ProduitRepository $ProdRepository): Response
    {
        return $this->render('home/prod.html.twig', [
            'produits' => $ProdRepository->findAll(),
        ]);
    }
}
